package com.epam.framework.test;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.epam.framework.logger.LoggerUtils;

public class AttachEmoticonsTest extends BaseTest {

	private static final int FIRST_EMOTICON = 1;
	private static final int SECOND_EMITICON = 20;

	@BeforeClass(description = "login in gmail")
	public void beforeClass() {
		steps.login(user1);
	}

	@AfterClass(description = "delete all letters")
	public void afterClass() {
		LoggerUtils.info("test was finished");
	}

	@Test
	public void sendingMessageWithEmoticons() {
		boolean condition = true;
		steps.clickCompose();
		steps.enterRecipient(user1);
		List<String> emoticons = steps.attachEmotcicons(FIRST_EMOTICON,
				SECOND_EMITICON);
		steps.clickSendInnewLetter();
		steps.openInbox();
		steps.openNewLetter();
		condition = steps.isTheSentEmoticonsAreAtTheTextArea(emoticons);
		if (condition) {
			LoggerUtils
					.info("Success.The sent emoticons are at the mail text area");
		}

		else
			LoggerUtils
					.error("Error.The sent emoticons are at the mail text area");
		Assert.assertFalse(condition, "Something wrong");
	}
}
