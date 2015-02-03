package com.epam.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.page.url.PagesURL;

public class MainPage extends Page {

	private final String BASE_URL = PagesURL.MAIN_PAGE_URL;

	@FindBy(id = "gmail-sign-in")
	private WebElement linkSignIn;

	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void open() {
		driver.navigate().to(BASE_URL);
		LoggerUtils.info("Navigate to " + BASE_URL);
	}

}
