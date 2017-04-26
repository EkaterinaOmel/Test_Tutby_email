package by.omel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TutByMainPage {

	private WebDriver driver;

	@FindBy(xpath = "//div[@id='mainmenu']//a[text()='Почта']")
	private WebElement mail;

	public TutByMainPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public SignInPage clickSignIn() {
		
		mail.click();
		return new SignInPage(driver);

	}
}
