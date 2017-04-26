package by.omel.pages;

import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexEmailPage {

	private WebDriver driver;

	@FindBy(xpath = "//a[contains(@class, 'fid-4')]")
	private WebElement menuItemSentMsgs;

	@FindBy(xpath = "//div[@class='mail-MessageSnippet-Content']")
	private List<WebElement> msgs;

	@FindBy(xpath = "//div[@class='js-content-toolbar-layout-switch']//div[@class='mail-ui-Arrow']")
	private WebElement layoutSettings;

	@FindBy(xpath = "//label[contains(@class, 'js-threaded-switch')]//input[@class='_nb-checkbox-input']")
	private WebElement msgsGroupBySubjectCheckbox;

	@FindBy(xpath = "//div[contains(@class,'b-mail-pager_more')]//button")
	private WebElement moreMsgsBtn;

	@FindBy(xpath = "//div[contains(@class, 'mail-ViewSettings-Group')]/span[1]")
	private WebElement expectedLayout;

	@FindBy(xpath = "//span[contains(@class, 'is-active')]")
	private WebElement activeLayout;

	public YandexEmailPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void goToSentMsgs() {
		menuItemSentMsgs.click();

	}

	public void clickOnLayoutSettings() {

		if (layoutSettings.isDisplayed())
			layoutSettings.click();
	}

	public void checkActivaLayout() {
		clickOnLayoutSettings();

		if (expectedLayout.isDisplayed())
			if (!expectedLayout.equals(activeLayout)) {
				expectedLayout.click();
			} else {
				clickOnLayoutSettings();
			}
	}

	public void checkMsgsGroupBySubject() {
		clickOnLayoutSettings();

		if (msgsGroupBySubjectCheckbox.isDisplayed())
			if (msgsGroupBySubjectCheckbox.isSelected()) {
				msgsGroupBySubjectCheckbox.click();
			} else {
				clickOnLayoutSettings();
			}
	}

	public void clickOnMoreMsgsBtn() {

		moreMsgsBtn.click();
		try {
			new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(moreMsgsBtn));
		} catch (TimeoutException e) {
			if (!moreMsgsBtn.isEnabled())
				System.out.println(e.getMessage());
		}

	}

	public void showAllMsgs() {

		while (moreMsgsBtn.isDisplayed())
			clickOnMoreMsgsBtn();

	}

	public int countMsgs() {
		goToSentMsgs();
		checkMsgsGroupBySubject();
		checkActivaLayout();
		showAllMsgs();
		return msgs.size();

	}

}
