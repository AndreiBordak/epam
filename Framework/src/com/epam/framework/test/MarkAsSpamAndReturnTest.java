package com.epam.framework.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.utils.Utils;

public class MarkAsSpamAndReturnTest extends BaseTest {

	@BeforeClass(description = "Login, sent new letter, open inbox page")
	public void beforeClass() {
		steps.login(user1);
		steps.sendLetter(Utils.getRandomString(6), user1);
		steps.openInbox();
	}

	@AfterClass(description = "Delete all leters in inbox")
	public void afterClass() {
		steps.deleteAllLetersInInbox();

	}

	@Test(description = "Mark item as spam and mark spam item as not spam")
	public void markImemAsSpamAndMarkSpamItemAsNotSpam() {
		boolean status = true;
		String messageInInbox = steps.addLetterToSpam();
		steps.openSpamPage();
		steps.markLetterAsNotSpam();
		steps.openInbox();
		String returnMessageInInbox = steps
				.getTopicAndBodyOfTheLetterInInboxFolder();
		status = returnMessageInInbox.equals(messageInInbox);
		if (!status) {
			LoggerUtils
					.error("Error.There is tested item in the list of inbox messages");
		}
		Assert.assertTrue(status, "something wrong");
		LoggerUtils
				.info("Success.There is tested item in the list of inbox messages");

	}
}
