package com.epam.framework.test;

import java.io.File;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.epam.framework.configuration.ConfigurationManager;
import com.epam.framework.configuration.PropertiesManager;
import com.epam.framework.configuration.constant.ConfigurationConstant;
import com.epam.framework.configuration.factory.ConfigurationFactory;
import com.epam.framework.driver.WebDriverTypes;
import com.epam.framework.entity.User;
import com.epam.framework.step.Steps;

public abstract class BaseTest {

	protected static Steps steps;
	protected static File file = null;
	protected User user1;
	protected User user2;
	protected User user3;
	
	@BeforeClass(description="Initializing browser")
	public void setUp(){
		steps = new Steps();
		ConfigurationFactory factory = ConfigurationFactory.getFactory();
		ConfigurationManager configManager = factory.createManager(PropertiesManager.class);
		initUsers(configManager);
		steps.initBrowser(WebDriverTypes.FIREFOX);
	}
	
	@AfterClass(description="Closing browser")
	public void stopBrowser(){
		steps.closeDriver();
	}
	
	private void initUsers(ConfigurationManager cm){
		HashMap<String, User> usersMap = cm.initUsers();
		user1 = usersMap.get(ConfigurationConstant.USER1);
		user2 = usersMap.get(ConfigurationConstant.USER2);
		user3 = usersMap.get(ConfigurationConstant.USER3);
	}

	
}
