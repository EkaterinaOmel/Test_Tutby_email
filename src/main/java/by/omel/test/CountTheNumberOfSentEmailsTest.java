package by.omel.test;

import org.testng.annotations.Test;

import by.omel.pages.SignInPage;
import by.omel.pages.TutByMainPage;
import by.omel.pages.YandexEmailPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class CountTheNumberOfSentEmailsTest {
	
	private WebDriver driver;
	private TutByMainPage tutByMainPage;
	private SignInPage signInPage;
	private YandexEmailPage yandexEmailPage;
	
	@Parameters({ "username", "password" })
	@Test
	public void countTheNumberOfSentEmails(String username, String password) {
		tutByMainPage = new TutByMainPage(driver);
		signInPage = tutByMainPage.clickSignIn();		
		yandexEmailPage = signInPage.verifySignIn(username, password);
		System.out.println("Number of sent messages: " + yandexEmailPage.countMsgs());
	}

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", "C:/webdrivers/geckodriver.exe");		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.tut.by");
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
