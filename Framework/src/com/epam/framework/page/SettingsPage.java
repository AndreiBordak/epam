package com.epam.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.page.exception.PageActionException;
import com.epam.framework.page.url.PagesURL;
import com.epam.framework.utils.Waiting;

public class SettingsPage extends Page {

	private static final String BASE_URL = PagesURL.SETTINGS_PAGE_URL;
	private final static String MESSAGE_SAVING = "//span[text()='Сохранение...']";
	private final static String MESSAGE_LOADING = "/span[text()='Загрузка...']";

	@FindBy(xpath = "//a[@class='f0 ou'][@href='https://mail.google.com/mail/#settings/fwdandpop']")
	private WebElement linkForwardingAndPopImap;

	@FindBy(xpath = "//a[@class='f0 ou'][@href='https://mail.google.com/mail/#settings/filters']")
	private WebElement linkFilters;

	@FindBy(xpath = "//a[@class='f0 ou'][@href='https://mail.google.com/mail/#settings/themes']")
	private WebElement linkThemes;

	@FindBy(xpath = "//input[@name='sx_sg'][@value='0']")
	private WebElement radioButtonSetNoSignature;

	@FindBy(xpath = "//button[@guidedhelpid='save_changes_button']")
	private WebElement buttonSaveChanges;

	@FindBy(xpath = "//div[@aria-label='Подпись']")
	private WebElement textOfSignature;

	@FindBy(xpath = "//input[ancestor::td[following-sibling::td//label[contains(text(),'Vacation responder on')]][@class='C6']]")
	private WebElement radioButtonVacationOn;

	@FindBy(xpath = "//input[@class='Da']")
	private WebElement textboxSubjectOfVacation;

	@FindBy(xpath = "//div[@aria-label='Vacation responder']")
	private WebElement textboxMessageOfVacation;

	public SettingsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public void goToForwardPage(){
		linkForwardingAndPopImap.click();
	}
	
	public void goToFiltersPage(){
		linkFilters.click();
	}
	
	public void goToThemesPage(){
		linkThemes.click();
		LoggerUtils.info("The themes page was opened");
	}
	
	@Override
	public void open() {
		driver.navigate().to(BASE_URL);
	}

	public void deleteSignatureAfterTest() throws PageActionException {
		textOfSignature.clear();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new PageActionException();
		}
		radioButtonSetNoSignature.click();
		buttonSaveChanges.click();
		Waiting.waitForElementInvisibility(driver, MESSAGE_SAVING, 10);
		Waiting.waitForElementInvisibility(driver, MESSAGE_LOADING, 10);
		LoggerUtils.info("The signature was deleted");
		
	}

	public void createSignature(String signature) throws PageActionException {
		textOfSignature.sendKeys(signature);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new PageActionException();
		}

		buttonSaveChanges.click();
		Waiting.waitForElementInvisibility(driver, MESSAGE_SAVING, 10);
		Waiting.waitForElementInvisibility(driver, MESSAGE_LOADING, 10);
		LoggerUtils.info("Signature was created");
	}

}
