package com.epam.framework.page;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.epam.framework.entity.User;
import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.page.attribute.Attribute;
import com.epam.framework.page.url.PagesURL;
import com.epam.framework.utils.Waiting;

public class SpamPage extends Page {

	private static final String BASE_URL = PagesURL.SPAM_PAGE_URL;
	private int count;
	@FindBy(xpath = "//div[@class='yW']/span")
	private List<WebElement> sendersOfAllLetter;

	@FindBy(xpath = "//div[@class='yW']")
	private WebElement senderOfFirstLetter;

	@FindBy(xpath = "//div[@class='T-Jo-auh']")
	private WebElement chooseAllLeterFromSpam;

	@FindBy(css = "div.T-I.J-J5-Ji.aFk.T-I-ax7.ar7")
	private WebElement markAsNotSpam;

	@FindBy(xpath = "//div[@class='akh J-J5-Ji J-JN-I']")
	private WebElement logoForWait;

	@FindBy(css = "div.oZ-jc.T-Jo.J-J5-Ji.T-Jo-JW")
	private WebElement chooseFirstLetter;

	@FindBy(xpath = "//span[@class='bofITb'][contains(text(),'Отметка спама удалена, а цепочка помещена во входящие')]")
	private WebElement messageMarkedAsNotSpam;

	@FindBy(xpath = "//div[@class='y6']")
	private WebElement topicAndBodyOfTheLetter;

	@FindBy(xpath = "//div[@class='T-Jo-auh'][ancestor::tr][ancestor::div[@style='']]")
	private WebElement radioButtonForChooseFirstLetter;

	{
		count = 0;
	}

	public SpamPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isLetterInSpam(User user) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			LoggerUtils.error(e);
		}
		driver.navigate().refresh();

		for (int i = 0; i < sendersOfAllLetter.size(); i++) {
			String email = sendersOfAllLetter.get(i).getAttribute(
					Attribute.EMAIL);
			if (email.equals(user.getEmail())) {
				count++;
			}
		}
		if (count > 0) {
			return true;
		}
		return false;
	}

	public String markLetterAsNotSpam() {
		Waiting.waitForElementVisible(driver, radioButtonForChooseFirstLetter,
				10);
		radioButtonForChooseFirstLetter.click();
		String topicAndBodyOfLetter = topicAndBodyOfTheLetter.getText();
		markAsNotSpam.click();
		Waiting.waitForElementVisible(driver, messageMarkedAsNotSpam, 5);
		Waiting.waitForElementInvisibility(driver, "topicAndBodyOfTheLetter",
				10);
		LoggerUtils.info("The letter was marked as not spam");
		return topicAndBodyOfLetter;

	}

	@Override
	public void open() {
		driver.navigate().to(BASE_URL);
		LoggerUtils.info("Navigate to " + BASE_URL);
	}

}
