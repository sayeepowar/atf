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

@Component("textboxHandler")
public class TextBoxHandler implements ElementHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(TextBoxHandler.class);

	public void handleElemnt(WebDriver webDriver, TestStepData testStepData) {
		logger.info("Handling text element with details:{}", testStepData);
		
		//find element
		WebElement element = webDriver.findElement(By.xpath(testStepData.getLocationXPath()));
		if ( element != null ) {
			//set value
			switch(testStepData.getAction()) {
			case SET_TEXT :
				logger.info("Setting input text for {} as {} ", testStepData.getName(), testStepData.getValue());
				element.sendKeys(testStepData.getValue());
				break;
			default :
				break;
			}			
		}
	}

	public void matchExpectedRes(WebDriver webDriver, TestExpectedResult testExpectedResult) throws Exception {
		logger.info("Checking expected results with {}", testExpectedResult);
		//find element
		WebElement element = webDriver.findElement(By.xpath(testExpectedResult.getLocationXPath()));
		if ( element != null ) {
			String actualText  = element.getText();
			String expectedREsult = testExpectedResult.getValue();
			if ( actualText == null 
					|| expectedREsult == null
					|| !actualText.equals(expectedREsult)) {
				throw new RuntimeException("Input Text does not match  to expectation. Expected:"+expectedREsult+", Actual:"+actualText);
			}
			else {
				logger.info("Matched Expectaion for {}", testExpectedResult.getName());
			}
		}
		
		
	}
}
