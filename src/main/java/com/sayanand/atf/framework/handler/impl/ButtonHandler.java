package com.sayanand.atf.framework.handler.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sayanand.atf.framework.beans.TestExpectedResult;
import com.sayanand.atf.framework.beans.TestStepData;
import com.sayanand.atf.framework.handler.ElementHandler;

@Component("buttonHandler")
public class ButtonHandler implements ElementHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(ButtonHandler.class);

	public void handleElemnt(WebDriver webDriver, TestStepData testStepData) {
		logger.info("Handling button element with details:{}", testStepData);

		// find element
		WebElement element = webDriver.findElement(By.xpath(testStepData.getLocationXPath()));
		if (element != null) {
			// set value
			switch (testStepData.getAction()) {
			case CLICK:
				logger.info("Firing click for {} ",testStepData.getName());
				element.click();
				break;
			default:
				break;
			}
		}

	}

	public void matchExpectedRes(WebDriver webDriver,
			TestExpectedResult testExpectedResult) throws Exception {
		// TODO Auto-generated method stub

	}

}
