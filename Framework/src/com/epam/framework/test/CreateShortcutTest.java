package com.epam.framework.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.page.exception.PageActionException;

public class CreateShortcutTest extends BaseTest {

	private static final String SHORTCUT_NAME = "My shortcut";
	private static final String INSERT_SHORTCUT_NAME = "Inserterd shortcut";

	@BeforeClass(description = "Login in gmail and create label")
	public void beforeClass() {
		steps.login(user1);
		steps.createNewparentLabel(SHORTCUT_NAME);
	}

	@AfterClass(description = "")
	public void afterClass() {

	}

	@Test(description = "Create inserted shortcut")
	public void createInseretedShortcut() throws PageActionException {
		boolean condition = true;
		steps.clickTriangle(SHORTCUT_NAME);
		steps.addInsertedlabel(SHORTCUT_NAME, INSERT_SHORTCUT_NAME);
		steps.clickArrowNearParentShortcut();
		condition = steps.isCreatedShortcutIsPresent(SHORTCUT_NAME,
				INSERT_SHORTCUT_NAME);

		if (!condition) {
			LoggerUtils.error("Error.Created insert shortcut is present");
		}
		Assert.assertTrue(condition);
		LoggerUtils.info("Success.Created insert shortcut is present");

	}
}
