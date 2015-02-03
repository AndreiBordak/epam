package com.epam.framework.main;


import java.util.HashMap;

import com.epam.framework.configuration.ConfigurationManager;
import com.epam.framework.configuration.ExcelManager;
import com.epam.framework.configuration.constant.ConfigurationConstant;
import com.epam.framework.configuration.factory.ConfigurationFactory;
import com.epam.framework.entity.User;
import com.epam.framework.page.exception.PageActionException;

public class Main {

	public static void main(String[] args) throws InterruptedException, PageActionException {

		User user1 = null;
		User user2 = null ;
		User user3 = null;
		ConfigurationFactory factory = ConfigurationFactory.getFactory();
		ConfigurationManager manager = factory.createManager(ExcelManager.class);
		HashMap<String,User> usersMap = manager.initUsers();
		user1 = usersMap.get(ConfigurationConstant.USER1);
		user2 = usersMap.get(ConfigurationConstant.USER2);
		user3 = usersMap.get(ConfigurationConstant.USER3);
		System.out.println(user1.toString());
		System.out.println(user2.toString());
		System.out.println(user3.toString());
	}
}
