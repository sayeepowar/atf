package com.sayanand.atf.candidate;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	LoginTest.class
})
public class ATFTestSuite {

	private static ApplicationContext context;
	@SuppressWarnings("resource")
	@BeforeClass
	public static void setup() {
		context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
	}
	
	@AfterClass
	public static void teardown(){
		context = null;
	}
}
