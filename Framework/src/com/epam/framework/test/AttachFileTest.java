package com.epam.framework.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.utils.Utils;


public class AttachFileTest extends BaseTest {

	private static final long FILE_SIZE=1024*1024*27;
	
	@BeforeClass(description="Login in gmail")
	public void beforeClass(){
		steps.login(user1);
	}
	
	@AfterClass(description = "Deletin created file")
	public void afterClass(){
		LoggerUtils.info("Created file was deleted");
		Utils.deleteFile(file);
	}
	
	@Test(description = "Try to attach file bigger than 25mb")
	public void tryingToaAttachFile(){
		boolean condition = true;
		steps.clickCompose();
		steps.enterRecipient(user2);
		steps.enterSubject();
		steps.enterMessage();
		file=steps.attachFile(FILE_SIZE);
		condition = steps.isWarningMessageAppeared();
		if(!condition){
			LoggerUtils.error("Size of a message biggest than 25mb");
		}
		Assert.assertTrue(condition,"Wrong!");
		LoggerUtils.info("Success");
	}
}
