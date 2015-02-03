package com.epam.framework.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.epam.framework.entity.User;
import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.page.attribute.Attribute;
import com.epam.framework.page.exception.PageActionException;
import com.epam.framework.page.url.PagesURL;
import com.epam.framework.utils.Utils;
import com.epam.framework.utils.Waiting;
import com.epam.framework.utils.exception.UtilException;

public class MailPage extends Page {

	private final String BASE_URL = PagesURL.MAIL_PAGE_URL;
	private static final String NEW_LETTER = "//div[@class='yW']/span[@class='zF'][ancestor::div[@class='ae4 aDM']]";
	private static final String TOPIC_AND_BODY_OF_THE_LETTER = "//div[@class='y6']";
	private static final String EMOTICONS_IN_NEW_LETTER = "//img[@goomoji]";
	private static final String ARROW_NEAR_MY_SHORTCUT = "//div[contains(@title,'Развернуть ярлык ')][contains(@title,'My shortcut')]";
	private static final String CHOOSE_ALL_LETTERS_FROM_INBOX = "//div[@class='T-Jo-auh']";
	private static final String SHORTCUT_OPTIONS_GENERALIZED_XPATH = "//div[@class='p8'][ancestor::div[preceding-sibling::div[descendant::a[@title='%s']]][@class='nL aig']][ancestor::div[@gh='cl']]";
	private static final String SHORTCUT_CHILD_OPTIONS_GENERALIZED_XPATH = "//a[@class='J-Ke n0'][preceding::a[@title='%s']][ancestor::div[@gh='cl']]";
	private static final String SHORTCUT_NAME_XPATH = "//div[@class='aio aip'][descendant::a[@title='%s']]";
	private static final String MESSAGE_THAT_SHORTCUT_WAS_REMOVED = "//div[@class='vh'][contains(text(),'Удалено')]";
	private static final String LOADING_BAR_WHEN_ATTACHED_FILE = "//div[@class='dR']";
	private static final String ERROR_MESSAGE_THAT_ATTACHED_FILE_BIGGER_THEN_25MB = "//span[@class='Kj-JD-K7-K0'][contains(text(),'25')]";
	private static final int SUBJECT_LENGTH = 5;

	@FindBy(xpath = MESSAGE_THAT_SHORTCUT_WAS_REMOVED)
	private WebElement messageThatShortcutWasRemoved;

	@FindBy(css = "div.T-I.J-J5-Ji.T-I-KE.L3")
	private WebElement buttonCompose;

	@FindBy(css = "textarea.vO")
	private WebElement editRecipient;

	@FindBy(css = "input.aoT")
	private WebElement editSubject;

	@FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
	private WebElement editTextOfLetter;

	@FindBy(css = "div.T-I.J-J5-Ji.aoO.T-I-atl.L3")
	private WebElement buttonSend;

	@FindBy(css = "div.oZ-jc.T-Jo.J-J5-Ji")
	private WebElement chooseLetter;

	@FindBy(css = "div.asl.T-I-J3.J-J5-Ji")
	private WebElement buttonAddToSpam;

	@FindBy(css = "div.vh")
	private WebElement confirmMessageForAddToSpam;

	@FindBy(xpath = CHOOSE_ALL_LETTERS_FROM_INBOX)
	private WebElement chooseAllLeterFromInbox;

	@FindBy(xpath = "//div[@aria-label='Удалить'][ancestor::div[@style='']]")
	private WebElement deleteAllLetters;

	@FindBy(xpath = "//div[@class='T-I J-J5-Ji ash T-I-ax7 L3']")
	private WebElement buttonSettings;

	@FindBy(xpath = "//div[@class='J-N aMS']")
	private WebElement buttonSettingsInContextMenu;

	@FindBy(xpath = "//div[@class='yW']/span[@class='zF'][@name='Gmail Team']")
	private WebElement linkToOpenConfirmLetterLetter;

	@FindBy(xpath = "//div[@class='a3s']/a[@target='_blank']")
	private WebElement linkToConfirmLetterForForwarding;

	@FindBy(xpath = "//div[@class='a1 aaA aMZ']")
	private WebElement buttonForAttachFile;

	@FindBy(xpath = "//td[text()='Confirmation Success!']")
	private WebElement confirmationSuccess;

	@FindBy(xpath = "//input[@name='q']")
	private WebElement searchField;

	@FindBy(xpath = "//button[@class='gbqfb']")
	private WebElement buttonSearch;

	@FindBy(xpath = NEW_LETTER)
	private WebElement newLetter;

	@FindBy(xpath = "//img[@class='ajz']")
	private WebElement imgInfoAboutLetter;

	@FindBy(xpath = "//div[@class='ajB gt']")
	private WebElement infoAboutLetter;

	@FindBy(xpath = "//div[@class='QT aaA aMZ']")
	private WebElement emoticonIcon;

	@FindBy(xpath = "//div[@goomoji]")
	private List<WebElement> emoticons;

	@FindBy(xpath = "//div[@class='aJJ']")
	private WebElement emotionsWindow;

	@FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-atl L3'][text()='Вставить']")
	private WebElement buttonInsert;

	@FindBy(xpath = "//span[@class='ag npDjle']")
	private WebElement messageThatLetterWasSend;

	@FindBy(xpath = "//img[@goomoji]")
	private WebElement emoticonsInLetter;

	@FindBy(xpath = "//div[@class='y6']")
	private WebElement topicAndBodyOfTheLetter;

	@FindBy(xpath = "//div[@class='gmail_signature']")
	private WebElement gmailSignature;

	@FindBy(xpath = "//img[@alt='Закрыть']")
	private WebElement buttonForCloseMessageWindow;

	@FindBy(xpath = "//img[@class='T-KT-JX']")
	private WebElement imgStarToSelect;

	@FindBy(xpath = "//a[contains(text(),'Создать ярлык')][ancestor::div[@class='n3']]")
	private WebElement buttonCreateNewLabel;

	@FindBy(xpath = "//a[contains(text(),'Управление ярлыками')][ancestor::div[@class='n3']]")
	private WebElement buttonManagelabels;

	@FindBy(xpath = "//span[contains(text(),'Ещё')]")
	private WebElement triangleMore;

	@FindBy(xpath = "//a[@title='Drafts']")
	private WebElement buttonDrafts;

	@FindBy(xpath = "//a[@title='Категории']")
	private WebElement buttonCategories;

	@FindBy(xpath = "//input[@class='xx']")
	private WebElement inputNewLabelName;

	@FindBy(xpath = "//button[@class='J-at1-auR']")
	private WebElement buttonCreateLabel;

	@FindBy(xpath = "//div[text()='Добавить вложенный ярлык']")
	private WebElement addSublabel;

	@FindBy(xpath = "//input[following-sibling::label[text()='Разместить ярлык под:']]")
	private WebElement checkBoxNestLabelUnder;

	@FindBy(xpath = "//select[@class='xx']")
	private WebElement selectedNameOfParentShortcut;

	@FindBy(xpath = ARROW_NEAR_MY_SHORTCUT)
	private WebElement arrowNearMyShortcut;

	@FindBy(xpath = "//span[ancestor::div[@class='J-N-Jz']]")
	private WebElement labelColor;

	@FindBy(xpath = "//div[@class='JA-Kn-Jr-Kw-Jt']")
	private List<WebElement> colorListForShortcut;

	@FindBy(xpath = "//td[descendant::div[@class='JA-Kn-Jr-Kw-Jt']]")
	private List<WebElement> parentOfcolorListForShortcut;

	@FindBy(xpath = "//input[@class='ajH'][following-sibling::label[contains(text(),'Ярлык')]]")
	private WebElement radioButtonLabelWithSublabels;

	@FindBy(xpath = "//button[@name='sc']")
	private WebElement buttonSetColorForShortcut;

	@FindBy(xpath = "//div[@class='J-N-Jz'][contains(text(),'Удалить ярлык')]")
	private WebElement removeLabel;

	@FindBy(xpath = "//button[contains(text(),'Удалить')]")
	private WebElement buttonDeleteShortcut;

	@FindBy(xpath = "//span[@class='ajP']")
	private List<WebElement> listWithShortcutsInDialog;

	public MailPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public void sendMessage(String message, User user) {
		buttonCompose.click();
		editRecipient.sendKeys(user.getEmail());
		editSubject.sendKeys(Utils.getRandomString(SUBJECT_LENGTH));
		editTextOfLetter.sendKeys(message);
		buttonSend.click();
		Waiting.waitForElementVisible(driver, messageThatLetterWasSend, 5);
		LoggerUtils.info("Message was sent");
	}

	public void clickSendInNewLetter() {
		buttonSend.click();
		Waiting.waitForElementVisible(driver, messageThatLetterWasSend, 5);
		LoggerUtils.info("sending");
	}

	public void markLetterAsSpam() throws PageActionException {
		try {
			Waiting.waitForElementVisible(driver, chooseLetter, 120);
			chooseLetter.click();
			Waiting.waitForElementVisible(driver, buttonAddToSpam, 10);
			buttonAddToSpam.click();
			Waiting.waitForElementVisible(driver, confirmMessageForAddToSpam,
					10);
			Waiting.waitForElementInvisibility(driver,
					TOPIC_AND_BODY_OF_THE_LETTER, 10);

		} catch (Exception e) {
			throw new PageActionException(e);
		}
		LoggerUtils.info("Letter was marked as spam");

	}

	public void deleteAllLetters() {
		if (Waiting.isElementPresent(driver, TOPIC_AND_BODY_OF_THE_LETTER)) {
			chooseAllLeterFromInbox.click();
			deleteAllLetters.click();
		}
		LoggerUtils.info("Letters in inbox folder were deleted");
	}

	public void clickSettings() {
		Waiting.waitForElementVisible(driver, buttonSettings, 5);
		buttonSettings.click();
		buttonSettingsInContextMenu.click();
		LoggerUtils.info("The setting page was opened");
	}

	public void logout(User user) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText(user.getEmail())).click();
		driver.findElement(By.linkText("Выйти")).click();
	}

	public void clickCompose() {
		buttonCompose.click();
		LoggerUtils.info("Click compose");
	}

	public void enterRecipient(User user) {
		editRecipient.sendKeys(user.getEmail());
		LoggerUtils.info("Editing recipient");
	}

	public void enterSubject() {
		editSubject.sendKeys(Utils.getRandomString(5));
		LoggerUtils.info("entering a subject of message");
	}

	public void enterMessage() {
		editTextOfLetter.sendKeys(Utils.getRandomString(15));
		LoggerUtils.info("entering a text of letter");
	}

	public File attachFile(long size) {
		LoggerUtils.info("try to attach file");
		buttonForAttachFile.click();
		File file = null;
		try {
			file = Utils.getRandomFile(size);
		} catch (UtilException e) {
			LoggerUtils.error(e);
		}
		StringSelection ss = new StringSelection(file.getAbsolutePath());
		LoggerUtils.info(file.getAbsolutePath());
		try {
			Utils.attachFile(ss);
		} catch (UtilException e) {
			LoggerUtils.error(e);
		}
		if (Waiting.isElementPresent(driver, LOADING_BAR_WHEN_ATTACHED_FILE)) {
			Waiting.waitForElementInvisibility(driver,
					LOADING_BAR_WHEN_ATTACHED_FILE, 120);
		}
		return file;
	}

	public boolean warningMessageForFile() {
		return Waiting.isElementPresent(driver,
				ERROR_MESSAGE_THAT_ATTACHED_FILE_BIGGER_THEN_25MB);
	}

	public List<String> attachEmoticons(int first, int second)
			throws PageActionException {
		List<String> emoticonsNames = new ArrayList<String>();
		emoticonIcon.click();
		Actions actions = new Actions(driver);
		emoticonsNames
				.add(emoticons.get(first).getAttribute(Attribute.GOOMOJI));
		actions.moveToElement(emoticons.get(second)).build().perform();
		Robot robot = null;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_SHIFT);
		} catch (AWTException e) {
			throw new PageActionException("Some problem with robot", e);
		}
		emoticons.get(second).click();
		emoticonsNames.add(emoticons.get(second).getAttribute("goomoji"));
		buttonInsert.click();
		LoggerUtils.info("Emoticons were attached");
		return emoticonsNames;
	}

	public void openInbox() {
		MailPage mailpage = new MailPage(driver);
		mailpage.open();
		LoggerUtils.info("inbox was opened");
	}

	@Override
	public void open() {
		driver.navigate().to(BASE_URL);
		LoggerUtils.info("Navigate to " + BASE_URL);
	}

	public void openNewLetter() {
		Waiting.waitForElementVisible(driver, newLetter, 60);
		if (Waiting.isElementPresent(driver, NEW_LETTER)) {
			newLetter.click();
		} else {
			LoggerUtils.error("There aren't new message");
		}
		LoggerUtils.info("new letter was opened");
	}

	public boolean isTheSentEmoticonsAreAtTheTextArea(List<String> emoticon) {
		List<String> emoticonsNames = new ArrayList<String>();
		if (Waiting.isElementPresent(driver, EMOTICONS_IN_NEW_LETTER)) {
			List<WebElement> emoticonsNamesElements = driver.findElements(By
					.xpath(EMOTICONS_IN_NEW_LETTER));
			for (WebElement e : emoticonsNamesElements) {
				emoticonsNames.add(e.getAttribute(Attribute.GOOMOJI));
			}

			if (emoticonsNames.containsAll(emoticon)
					&& emoticonsNames.size() == emoticon.size())
				return true;
		}
		return false;
	}

	public void createNewLabel(String shortcutName) {
		triangleMore.click();
		buttonCreateNewLabel.click();
		Waiting.waitForElementVisible(driver, inputNewLabelName, 4);
		inputNewLabelName.sendKeys(shortcutName);
		buttonCreateLabel.click();
		LoggerUtils.info("Shortcut created");
	}

	public void clickTriangle(String shortcutName) {
		Actions action = new Actions(driver);
		action.moveToElement(
				driver.findElement(By.xpath(String.format(SHORTCUT_NAME_XPATH,
						shortcutName)))).build().perform();
		WebElement triangleNearShortCut = driver.findElement(By.xpath(String
				.format(SHORTCUT_OPTIONS_GENERALIZED_XPATH, shortcutName)));
		Waiting.waitForElementVisible(driver, triangleNearShortCut, 2);
		triangleNearShortCut.click();
		LoggerUtils.info("Triangle near shortcut was clicked");
	}

	public void addinsertedlabel(String shortcutName, String insertShortcutName)
			throws PageActionException {
		Waiting.waitForElementVisible(driver, addSublabel, 2);
		addSublabel.click();
		Waiting.waitForElementVisible(driver, inputNewLabelName, 2);
		inputNewLabelName.sendKeys(insertShortcutName);
		if (checkBoxNestLabelUnder.isSelected()
				&& selectedNameOfParentShortcut.getText()
						.contains(shortcutName)) {
			buttonCreateLabel.click();
		} else {
			throw new PageActionException(
					"No such element: Wrong parent of insert shortcut of thr time of creation");
		}
		LoggerUtils.info("inserted shortcut added");
	}

	public void clickArrowNearParentShortcut() {
		if (Waiting.isElementPresent(driver, ARROW_NEAR_MY_SHORTCUT)) {
			arrowNearMyShortcut.click();
			LoggerUtils.info("Arrow near parent shortcut was clicked");
		}
	}

	public boolean isCreatedShortcutIsPresent(String shortcutName,
			String insertShortcutName) throws PageActionException {
		if (Waiting.isElementNotPresent(driver, String.format(
				SHORTCUT_CHILD_OPTIONS_GENERALIZED_XPATH, shortcutName))) {
			throw new PageActionException("Error.Created shortcut is present");
		}
		WebElement createdInsertShortcut = driver
				.findElement(By.xpath(String.format(
						SHORTCUT_CHILD_OPTIONS_GENERALIZED_XPATH, shortcutName)));
		if (createdInsertShortcut.getText().contains(insertShortcutName)) {
			return true;
		}

		return false;
	}

	public void clickLabelColorForShortcut() {
		Waiting.waitForElementVisible(driver, labelColor, 3);
		Actions action = new Actions(driver);
		action.moveToElement(labelColor).build().perform();
		LoggerUtils.info("Label color for shortcut was clicked");
	}

	public void changeShortcutColor(int color) {
		Actions action = new Actions(driver);
		action.moveToElement(colorListForShortcut.get(color)).build().perform();
		colorListForShortcut.get(color).click();
		Waiting.waitForElementVisible(driver, radioButtonLabelWithSublabels, 2);
		radioButtonLabelWithSublabels.click();
		buttonSetColorForShortcut.click();
		LoggerUtils.info("Shortcut color was changed");
	}

	public boolean isColorOfShortcutWasChanged(int color) {
		Actions action = new Actions(driver);
		try {
			WebElement e = driver
					.findElement(By
							.xpath("//div[@class='J-awr J-awr-JE'][contains(text(),'color')]"));
			action.moveToElement(e);
		} catch (Exception e) {

		}
		colorListForShortcut.get(color).click();
		if (parentOfcolorListForShortcut.get(color)
				.getAttribute(Attribute.ARIA_SELECTED).equals("true")) {

			return true;
		}
		return true;
	}

	public void deleteShortcut(String shortcutName, String insertShortcutName) {
		removeLabel.click();
		LoggerUtils.info("Button remove was clicked");
		String name = "";
		for (WebElement e : listWithShortcutsInDialog) {
			String currentName = e.getText().concat(" ");
			name = name.concat(currentName);
		}
		if (name.contains(shortcutName + " " + insertShortcutName)) {
			LoggerUtils
					.info("The names of borth shortcuts are present at the dialog ");
			buttonDeleteShortcut.click();
			LoggerUtils.info("Button Delete was clicked");

			Waiting.waitForElementVisible(driver,
					messageThatShortcutWasRemoved, 10);

		} else {
			Assert.fail();
			LoggerUtils
					.error("The names of borth shortcuts are not present at the dialog");
		}
	}

	public boolean isBorthShortcutsWereDeleted(String shortcutName,
			String insertShortcutName) {
		boolean status = false;
		if (Waiting.isElementNotPresent(driver,
				String.format(SHORTCUT_NAME_XPATH, shortcutName))
				&& Waiting.isElementNotPresent(driver,
						String.format(SHORTCUT_NAME_XPATH, insertShortcutName))) {
			status = true;
		}
		return status;
	}

	public String addLetterToSpam() {
		Waiting.waitForElementVisible(driver, chooseLetter, 120);
		String topicAndBodyOfLetter = topicAndBodyOfTheLetter.getText();
		chooseLetter.click();
		Waiting.waitForElementVisible(driver, buttonAddToSpam, 10);
		buttonAddToSpam.click();
		Waiting.waitForElementVisible(driver, confirmMessageForAddToSpam, 10);
		LoggerUtils.info("Letter was added to spam");
		return topicAndBodyOfLetter;

	}

	public String getTopicAndBodyOfTheLetter() {
		return topicAndBodyOfTheLetter.getText();
	}

	public String getSignatureOfNewMessage() {
		return gmailSignature.getText();
	}

	public void closeMessageWindow() {
		buttonForCloseMessageWindow.click();		
	}
}
