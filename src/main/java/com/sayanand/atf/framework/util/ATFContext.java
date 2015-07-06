package com.sayanand.atf.framework.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ATFContext implements ApplicationContextAware {

	private static ApplicationContext appContext;

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		appContext = arg0;
	}

	public static ApplicationContext getApplicationContext() {
		return appContext;
	}
	
	public static Object getBean(String name) {
		return appContext.getBean(name);
	}

}
