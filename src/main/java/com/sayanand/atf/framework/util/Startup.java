package com.sayanand.atf.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sayanand.atf.framework.TestRun;

public class Startup {

	private static final Logger logger = LoggerFactory.getLogger(Startup.class);
	
	public static void main(String[] args) {
		try {
			ApplicationContext appContext = new ClassPathXmlApplicationContext("config/applicationContext.xml");
			TestRun testRun = (TestRun)appContext.getBean("testRun");
			testRun.executeTest("CANDIDATE LOGIN");
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
