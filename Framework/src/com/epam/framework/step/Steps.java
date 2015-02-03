package com.epam.framework.step;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.epam.framework.driver.Driver;
import com.epam.framework.driver.WebDriverTypes;
import com.epam.framework.entity.User;
import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.page.LoginPage;
import com.epam.framework.page.MailPage;
import com.epam.framework.page.SettingsPage;
import com.epam.framework.page.SpamPage;
import com.epam.framework.page.ThemesPage;
import com.epam.framework.page.exception.PageActionException;

public class Steps {

	private WebDriver driver;

	public Steps() {

	}

	// init browser
	public void initBrowser(WebDriverTypes type) {
		Driver.setDriver(type);
		driver = Driver.getInstance();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		LoggerUtils.info("Browser " + type.toString() + " started");

	}

	// closing driver
	public void closeDriver() {
		Driver.closeDriver();
	}

	// login user
	public void login(User user) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.open();
		loginPage.signIn(user);
	}

	// logout user
	public void logout(User user) {
		MailPage mailPage = new MailPage(driver);
		mailPage.logout(user);
	}

	// login other account
	public void loginOtherAccount() {
		LoginPage loginPage = new LoginPage(driver);
		try {
			loginPage.loginAsOtherAccount();
		} catch (Exception e) {
			LoggerUtils.error(e);
		}
	}

	// sending letter
	public void sendLetter(String message, User user) {
		MailPage mailPage = new MailPage(driver);
		mailPage.sendMessage(message, user);
	}

	// mark letter from user as spam
	public void markLetterAsSpam() {
		MailPage mailPage = new MailPage(driver);
		try {
			mailPage.markLetterAsSpam();
		} catch (PageActionException e) {
			LoggerUtils.error(e);
		}
	}

	// checking for letter in spam
	public boolean isLetterinSpam(User user) {
		SpamPage spamPage = new SpamPage(driver);
		spamPage.open();
		return spamPage.isLetterInSpam(user);
	}

	// click button "compose" letter
	public void clickCompose() {
		MailPage mailPage = new MailPage(driver);
		mailPage.clickCompose();
	}

	// edit recipient of letter
	public void enterRecipient(User user) {
		MailPage mailPage = new MailPage(driver);
		mailPage.enterRecipient(user);
	}

	// enter random subject
	public void enterSubject() {
		MailPage mailPage = new MailPage(driver);
		mailPage.enterSubject();
	}

	// enter random letter
	public void enterMessage() {
		MailPage mailpage = new MailPage(driver);
		mailpage.enterMessage();
	}

	// attach file to letter
	public File attachFile(long size) {
		MailPage mailPage = new MailPage(driver);
		return mailPage.attachFile(size);
	}

	// warning message for attaching big file
	public boolean isWarningMessageAppeared() {
		MailPage mailPage = new MailPage(driver);
		return mailPage.warningMessageForFile();
	}

	public void clickSendInnewLetter() {
		MailPage mailPage = new MailPage(driver);
		mailPage.clickSendInNewLetter();
	}

	// method for set new driver
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	// method for open inbox
	public void openInbox() {
		MailPage mailPage = new MailPage(driver);
		mailPage.openInbox();
	}

	// method for attach emoticons
	public List<String> attachEmotcicons(int firstEmoticon, int secondEmiticon) {
		MailPage mailPage = new MailPage(driver);
		List<String> emoticons = null;
		try {
			emoticons = mailPage.attachEmoticons(firstEmoticon, secondEmiticon);
		} catch (PageActionException e) {
			LoggerUtils.error(e);
		}
		return emoticons;
	}

	public void openNewLetter() {
		MailPage mailPage = new MailPage(driver);
		mailPage.openNewLetter();
	}

	public boolean isTheSentEmoticonsAreAtTheTextArea(List<String> emoticons) {
		MailPage mailPage = new MailPage(driver);
		return mailPage.isTheSentEmoticonsAreAtTheTextArea(emoticons);
	}

	public void deleteAllLetersInInbox() {
		MailPage mailPage = new MailPage(driver);
		mailPage.deleteAllLetters();
	}

	public void chooseLightTheme() {
		ThemesPage themePage = new ThemesPage(driver);
		themePage.chooseLightTheme();
	}

	public void openSettingsPage() {
		MailPage mailPage = new MailPage(driver);
		mailPage.clickSettings();
	}

	public void openThemesPage() {
		SettingsPage settingPage = new SettingsPage(driver);
		settingPage.goToThemesPage();
	}

	public void chooseBeachTheme() {
		ThemesPage themePage = new ThemesPage(driver);
		themePage.chooseBeachTheme();
	}

	public boolean isBeachThemeChoosen() {
		ThemesPage themePage = new ThemesPage(driver);
		return themePage.isBeachThemeWasChoosen();
	}

	public void createNewparentLabel(String shortcutName) {
		MailPage mailPage = new MailPage(driver);
		mailPage.createNewLabel(shortcutName);
	}

	public void clickTriangle(String shortcutName) {
		MailPage mailPage = new MailPage(driver);
		mailPage.clickTriangle(shortcutName);
	}

	public void addInsertedlabel(String shortcutName, String insertShortcutName) {
		MailPage mailPage = new MailPage(driver);
		try {
			mailPage.addinsertedlabel(shortcutName, insertShortcutName);
		} catch (PageActionException e) {
			LoggerUtils.error(e);
		}
	}

	public void clickArrowNearParentShortcut() {
		MailPage mailPage = new MailPage(driver);
		mailPage.clickArrowNearParentShortcut();
	}

	public boolean isCreatedShortcutIsPresent(String shortcutName,
			String insertShortcutName) {
		MailPage mailPage = new MailPage(driver);
		boolean condition=true;
		try {
			condition=  mailPage.isCreatedShortcutIsPresent(shortcutName,
					insertShortcutName);
		} catch (PageActionException e) {
			condition = false;
			LoggerUtils.error(e);
		}
		return condition;
	}

	public void clickLabelColorForShortcut() {
		MailPage mailPage = new MailPage(driver);
		mailPage.clickLabelColorForShortcut();
	}

	public void changeShortcutColor(int color) {
		MailPage mailPage = new MailPage(driver);
		mailPage.changeShortcutColor(color);
	}

	public boolean isColorOfShortcutWasChanged(int color) {
		MailPage mailPage = new MailPage(driver);
		return mailPage.isColorOfShortcutWasChanged(color);
	}

	public void deleteShortcut(String shortcutName, String insertShortcutName) {
		MailPage mailPage = new MailPage(driver);
		mailPage.deleteShortcut(shortcutName, insertShortcutName);

	}

	public boolean isBorthShortcutsWereDeleted(String shortcutName,
			String insertShortcutName) {
		MailPage mailPage = new MailPage(driver);
		return mailPage.isBorthShortcutsWereDeleted(shortcutName,
				insertShortcutName);
	}

	public String addLetterToSpam() {
		MailPage mailPage = new MailPage(driver);
		String topicAndBodyOfTheLetterInInbox = mailPage.addLetterToSpam();
		return topicAndBodyOfTheLetterInInbox;
	}

	public void openSpamPage() {
		SpamPage spamPage = new SpamPage(driver);
		spamPage.open();
	}

	public String markLetterAsNotSpam() {
		SpamPage spamPage = new SpamPage(driver);
		String topicAndBodyOfTheLetterInSpam = spamPage.markLetterAsNotSpam();
		return topicAndBodyOfTheLetterInSpam;
	}

	public String getTopicAndBodyOfTheLetterInInboxFolder() {
		MailPage mailPage = new MailPage(driver);
		return mailPage.getTopicAndBodyOfTheLetter();
	}

	public void deleteSignatureAfterTest() {
		SettingsPage settingPage = new SettingsPage(driver);
		try {
			settingPage.deleteSignatureAfterTest();
		} catch (PageActionException e) {
			LoggerUtils.error(e);
		}
	}

	public void createSignature(String signature) {
		SettingsPage settingPage = new SettingsPage(driver);
		try {
			settingPage.createSignature(signature);
		} catch (PageActionException e) {
			LoggerUtils.error(e);
		}
	}

	public String getSignatureOfNewMessage() {
		MailPage mailPage = new MailPage(driver);
		return mailPage.getSignatureOfNewMessage();
	}

	public void closeMessageWindow() {
		MailPage mailPage = new MailPage(driver);
		mailPage.closeMessageWindow();
	}

}
