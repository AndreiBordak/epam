package com.epam.framework.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.utils.Utils;

public class SpamTest extends BaseTest {

	@BeforeClass(description="SignIn")
	public void beforeclass(){
		LoggerUtils.info("before");
		steps.login(user1);
		LoggerUtils.info("after");
	}
	
	@AfterClass(description ="Logout")
	public void afterClass(){
		
	}
	
	@Test(description = "Adding letter in spam and checking it")
	public void testMarkLetterAsSpam(){
		steps.sendLetter(Utils.getRandomString(6), user2);
		steps.logout(user1);
		steps.loginOtherAccount();
		steps.login(user2);
		steps.markLetterAsSpam();
		Assert.assertTrue(steps.isLetterinSpam(user1));
	}

}
