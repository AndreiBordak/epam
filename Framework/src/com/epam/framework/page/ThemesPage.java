package com.epam.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.page.url.PagesURL;
import com.epam.framework.utils.Waiting;

public class ThemesPage extends Page {

	private final String BASE_URL = PagesURL.THEMES_PAGE_URL;
	//private WebDriverWait driverWait = new WebDriverWait(driver, 10);
	//private final static String MESSAGE_WITH_ERROR = "//div[@class='d-Jb d-Jb-Lb d-Jb-Ob']";

	@FindBy(xpath = "//span[text()='Custom Light']")
	private WebElement linkCustomTheme;

	@FindBy(xpath = "//div[text()='Upload photos']")
	private WebElement buttonUploadPhotos;

	@FindBy(xpath = "//iframe[@class='KA-JQ']")
	private WebElement frameForChangePhoto;

	@FindBy(xpath = "//div[text()='Select photos from your computer']")
	private WebElement buttonSelectPhotosFromYourComputer;

	@FindBy(xpath = "//div[@class='d-Jb d-Jb-Lb d-Jb-Ob']/span")
	private WebElement messageUploadError;

	@FindBy(xpath = "//span[@class='sf']")
	private WebElement curentTheme;

	@FindBy(xpath = "//span[text()='Пляж']")
	private WebElement beachTheme;

	@FindBy(xpath = "//span[text()='Светлая']")
	private WebElement lightTheme;

	@FindBy(xpath = "//div[@class='vh'][contains(text(),'Настройки сохранены.')]")
	private WebElement messageThatChoosenThemeWasSet;

	public ThemesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void open() {
		driver.navigate().to(BASE_URL);

	}

	public void chooseLightTheme() {
		lightTheme.click();
		Waiting.waitForElementVisible(driver, messageThatChoosenThemeWasSet, 10);
		LoggerUtils.info("The default theme was choosen");
	}

	public void chooseBeachTheme() {
		beachTheme.click();
		Waiting.waitForElementVisible(driver, messageThatChoosenThemeWasSet, 10);
		LoggerUtils.info("Beach Theme was clicked");
	}

	public boolean isBeachThemeWasChoosen() {
		driver.navigate().refresh();
		if (curentTheme.getText().equals("Пляж")) {
			return true;
		}
		return false;
	}

}
