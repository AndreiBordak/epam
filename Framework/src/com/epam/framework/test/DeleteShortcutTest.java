package com.epam.framework.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.framework.logger.LoggerUtils;

public class DeleteShortcutTest extends BaseTest {

	private static final String SHORTCUT_NAME = "My shortcut";
	private static final String INSERT_SHORTCUT_NAME = "Inserterd shortcut";
	
	@BeforeClass(description = "Login")
	public void beforeClass() {
		steps.login(user1);
	}

	@AfterClass(description = "")
	public void afterClass() {

	}

	@Test(description = "Delete shortcut")
	public void deleteShortcut() {
		boolean status = true;
		steps.clickTriangle(SHORTCUT_NAME);
		steps.deleteShortcut(SHORTCUT_NAME, INSERT_SHORTCUT_NAME);
		status = steps.isBorthShortcutsWereDeleted(SHORTCUT_NAME,
				INSERT_SHORTCUT_NAME);

		if (!status) {
			LoggerUtils.error("Error.Borth shortcuts were deleted");
		}
		Assert.assertTrue(status);
		LoggerUtils.info("Success.Borth shortcuts were deleted");

	}

}
