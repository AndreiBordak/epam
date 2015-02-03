package com.epam.framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.epam.framework.logger.LoggerUtils;


public class Driver {
	private static WebDriver driver;
	private static WebDriverTypes type;
	
	public static WebDriver getInstance(){
		return (driver==null)? createnewWebDriverInstance():driver;
	}
	
	private Driver(){
		
	}

	private static WebDriver createnewWebDriverInstance() {
		
		
		switch(type){
		case IE:
			System.setProperty("webdriver.ie.driver", "C:\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default:
			driver = new FirefoxDriver();
			break;
		}
		return driver;
	}

	public static void setDriver(WebDriverTypes desiredType){
		type = desiredType;
	}
	

	public static void closeDriver(){
		driver.quit();
		driver = null;
		LoggerUtils.info("Browser closed");
	}

}
