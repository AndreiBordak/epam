package com.epam.framework.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.utils.Utils;

public class CheckingSignatureTest extends BaseTest {

	@BeforeClass(description = "Login")
	public void beforeClass() {
		steps.login(user1);
	}

	@AfterClass(description = "Delete created signature")
	public void afterClass() {
		steps.openSettingsPage();
		steps.deleteSignatureAfterTest();
	}

	@Test(description = "Cheking signature")
	public void newMessageHasSignature() {
		boolean status = true;
		String signature = Utils.getRandomString(5);
		steps.openSettingsPage();
		steps.createSignature(signature);
		steps.clickCompose();
		steps.getSignatureOfNewMessage();
		status = steps.getSignatureOfNewMessage().equals(signature);
		if (!status) {
			LoggerUtils.error("Error.New message has text equals signature");
		}
		Assert.assertTrue(status, "Something wrong");
		LoggerUtils.info("Success.New message has text equals signature");
		steps.closeMessageWindow();

	}

}
