package com.epam.framework.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.framework.entity.User;
import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.page.url.PagesURL;
import com.epam.framework.utils.Waiting;

public class LoginPage extends Page {

	private final String BASE_URL = PagesURL.LOGIN_PAGE_URL;

	@FindBy(xpath = "id('Email')")
	private WebElement inputLogin;

	@FindBy(xpath = "id('Passwd')")
	private WebElement inputPassword;

	@FindBy(xpath = "id('signIn')")
	private WebElement buttonSignIn;

	@FindBy(className = "remember")
	private WebElement checkStaySignedIn;

	private WebElement otherAccount;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);

	}

	public void clickOnLabelStaysignedIn() {
		checkStaySignedIn.click();
	}

	public void signIn(User user) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		inputLogin.sendKeys(user.getEmail());
		inputPassword.sendKeys(user.getPassword());
		clickOnLabelStaysignedIn();
		buttonSignIn.click();
		Waiting.waitForElementInvisibility(driver, "//div[@class='lpb']", 120);
		LoggerUtils.info("Login succes");
	}
	
	public void loginAsOtherAccount(){
		otherAccount = driver.findElement(By.linkText("Войти ещё в один аккаунт"));
		otherAccount.click();
	}

	@Override
	public void open() {
		driver.navigate().to(PagesURL.LOGIN_PAGE_URL);
		LoggerUtils.info("Navigate to " + BASE_URL);
	}

}
