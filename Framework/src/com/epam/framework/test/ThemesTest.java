package com.epam.framework.test;

import org.testng.annotations.BeforeClass;

public class ThemesTest extends BaseTest {

	@BeforeClass(description = "login in gmail")
	public void beforeClass(){
		steps.login(user1);
	}
	
	

}
