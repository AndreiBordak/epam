package com.epam.framework.configuration;

import java.util.HashMap;
import java.util.ResourceBundle;

import com.epam.framework.configuration.constant.ConfigurationConstant;
import com.epam.framework.entity.User;

public class PropertiesManager extends ConfigurationManager{

	private static final String RESOURCE = "configuration.users";
	private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE);
	private static PropertiesManager instance = null;
	
	private PropertiesManager(){
		super();
	}
	
	public synchronized static PropertiesManager getInstance(){
		if (instance == null){
			instance = new PropertiesManager();
		}
		return instance;
	}
	
	public String getProperty(String key){
		return resourceBundle.getString(key);
	}
	
	public HashMap<String, User> initUsers(){
		HashMap<String, User> usersMap = new HashMap<String, User>();
		usersMap.put(ConfigurationConstant.USER1, new User(PropertiesManager.getInstance()
				.getProperty(ConfigurationConstant.FIRST_USERNAME),
				PropertiesManager.getInstance().getProperty(
						ConfigurationConstant.FIRST_USERPWD)));
		usersMap.put(ConfigurationConstant.USER2, new User(PropertiesManager.getInstance()
				.getProperty(ConfigurationConstant.SECOND_USERNAME),
				PropertiesManager.getInstance().getProperty(
						ConfigurationConstant.SECOND_USERPWD)));
		usersMap.put(ConfigurationConstant.USER3, new User(PropertiesManager.getInstance()
				.getProperty(ConfigurationConstant.THIRD_USERNAME),
				PropertiesManager.getInstance().getProperty(
						ConfigurationConstant.THIRD_USERPWD)));
		
		return usersMap;
	}
}
