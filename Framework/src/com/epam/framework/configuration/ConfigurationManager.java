package com.epam.framework.configuration;

import java.util.HashMap;

import com.epam.framework.entity.User;

public abstract class ConfigurationManager {

	public ConfigurationManager() {

	}

	public abstract HashMap<String, User> initUsers();

}
