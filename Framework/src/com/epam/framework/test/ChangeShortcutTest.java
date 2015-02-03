package com.epam.framework.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.framework.logger.LoggerUtils;

public class ChangeShortcutTest extends BaseTest {

	private static final String SHORTCUT_NAME = "My shortcut";
	private static final String INSERT_SHORTCUT_NAME = "Inserterd shortcut";
	
	@BeforeClass(description = "Login")
	public void beforeClass() {
		steps.login(user1);
	}

	@AfterClass(description ="")
	public void afterClass() {

	}
	
	@Test(description = "Change shortcut")
	public void changeShortcut() {
		boolean condition = true;

		steps.clickTriangle(SHORTCUT_NAME);
		steps.clickLabelColorForShortcut();
		steps.changeShortcutColor(6);
		steps.clickArrowNearParentShortcut();
		steps.clickTriangle(SHORTCUT_NAME);
		steps.clickLabelColorForShortcut();
		if (steps.isColorOfShortcutWasChanged(6)) {
			LoggerUtils.info("Color of parent shortcut was changed");
		} else {
			condition = false;
			LoggerUtils.info("Color of parent shortcut was not changed");
		}
		steps.clickTriangle(INSERT_SHORTCUT_NAME);
		steps.clickLabelColorForShortcut();

		if (steps.isColorOfShortcutWasChanged(6)) {
			LoggerUtils.info("The color of insert shortcut was changed");
		} else {
			condition = false;
			LoggerUtils.info("The color of insert shortcut was not changed");
		}

		if (!condition) {
			LoggerUtils
					.error("Error.The colour of both shortcuts is the same as choosen");
		}
		Assert.assertTrue(condition);
		LoggerUtils
				.info("Success.The colour of both shortcuts is the same as choosen");

	}
}
