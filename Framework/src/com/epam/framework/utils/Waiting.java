package com.epam.framework.utils;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiting {

	private final static int DEFAULT_IMPLICITY_WAIT = 15;

	public static boolean isElementPresent(WebDriver webDriver,
			final String elementLocator) {
		webDriver.manage().timeouts()
				.implicitlyWait(DEFAULT_IMPLICITY_WAIT, TimeUnit.SECONDS);

		return webDriver.findElements(By.xpath(elementLocator)).size() > 0;
	}

	public static boolean isElementNotPresent(WebDriver webDriver,
			final String elementLocator) {
		boolean status = true;
		webDriver.manage().timeouts()
				.implicitlyWait(DEFAULT_IMPLICITY_WAIT, TimeUnit.SECONDS);
		if (webDriver.findElements(By.xpath(elementLocator)).size() != 0) {
			status = !webDriver.findElement(By.xpath(elementLocator))
					.isDisplayed();
		}
		return status;
	}

	public static boolean isElementPresent(WebDriver webDriver,
			final String elementLocator, int time) {
		webDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		final int numberOfElements = webDriver.findElements(
				By.xpath(elementLocator)).size();
		if (numberOfElements > 0) {
			return true;
		}

		return false;
	}

	public static boolean isElementNotPresent(WebDriver webDriver,
			final String elementLocator, int time) {
		boolean status = true;
		webDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		if (webDriver.findElements(By.xpath(elementLocator)).size() != 0) {
			status = !webDriver.findElement(By.xpath(elementLocator))
					.isDisplayed();
		}
		return status;
	}

	public static void waitForElementVisible(WebDriver webDriver,
			WebElement webElement, int time) {
		WebDriverWait driverWait = new WebDriverWait(webDriver, time);
		driverWait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public static void waitForElementInvisibility(WebDriver webDriver,
			final String elementLocator, int time) {
		WebDriverWait driverWait = new WebDriverWait(webDriver, time);
		driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.xpath(elementLocator)));

	}
}
