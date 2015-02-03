package com.epam.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.framework.page.url.PagesURL;

public class ForwardPage extends Page {

	private static final String BASE_URL = PagesURL.FORWARD_PAGE_URL;
	
	@FindBy(xpath = "//input[@type='button']")
	private WebElement buttonAddAForwardingAdress;

	@FindBy(xpath = "//div[@class='PN']/input[@type='text']")
	private WebElement editForwardingEmail;

	@FindBy(xpath = "//button[@class='J-at1-auR'][@name='next']")
	private WebElement buttonNextAfterEnteringForwardingAdress;

	@FindBy(xpath = "//iframe[@class='ds']")
	private WebElement frame;

	@FindBy(xpath = "//input[@type='submit'][@value='Proceed']")
	private WebElement buttonProceedForConfirmAdress;

	@FindBy(xpath = "//button[@class='J-at1-auR'][@name='ok']")
	private WebElement buttonConfirmOk;

	@FindBy(xpath = "//input[@name='sx_em'][@value='1']")
	private WebElement radioButtonForwarardACopyOfIncomingMail;

	@FindBy(xpath = "//button[ancestor::div[@class='nH Tv1JD']][text()='Save Changes']")
	private WebElement buttonSaveChanges;

	@FindBy(xpath = "//span[@class='v1']")
	private WebElement loading;
	
	public ForwardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	

	@Override
	public void open() {
		driver.navigate().to(BASE_URL);
	}

}
