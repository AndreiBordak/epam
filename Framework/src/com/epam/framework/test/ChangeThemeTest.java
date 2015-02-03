package com.epam.framework.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.framework.logger.LoggerUtils;

public class ChangeThemeTest extends BaseTest {

	@BeforeClass(description = "Login")
	public void beforeClass() {
		steps.login(user1);
	}

	@AfterClass(description = "Delete choosen theme")
	public void afterClass() {
		steps.chooseLightTheme();
		steps.closeDriver();
	}

	@Test(description = "Theme")
	public void theThemeWasChanged() {
		boolean status = true;

		steps.openSettingsPage();
		steps.openThemesPage();
		steps.chooseBeachTheme();
		status = steps.isBeachThemeChoosen();
		if (status) {
			LoggerUtils.info("Success.The beach theme was set");

		} else {
			LoggerUtils.error("Error. The beach theme was set");

		}
		Assert.assertTrue(status, "Something wrong");

	}
}
