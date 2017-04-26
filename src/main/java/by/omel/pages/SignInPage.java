package by.omel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

	private WebDriver driver;

	@FindBy(id = "Username")
	private WebElement usernameInput;

	@FindBy(id = "Password")
	private WebElement passwordInput;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginBtn;

	@FindBy(xpath = "//form//strong")
	private WebElement errorMsgTxt;

	public SignInPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public YandexEmailPage verifySignIn(String username, String password) {
		enterUserName(username);
		enterPassword(password);
		clickOnSignIn();

		return new YandexEmailPage(driver);
		
	}

	public void enterUserName(String userName) {

		if (usernameInput.isDisplayed())
			usernameInput.sendKeys(userName);
	}

	public void enterPassword(String password) {

		if (passwordInput.isDisplayed())
			passwordInput.sendKeys(password);
	}

	public void clickOnSignIn() {

		if (loginBtn.isDisplayed())
			loginBtn.click();
	}

	
}
