package com.sayanand.atf.framework.handler;

import org.openqa.selenium.WebDriver;

import com.sayanand.atf.framework.beans.TestExpectedResult;
import com.sayanand.atf.framework.beans.TestStepData;

public interface ElementHandler {

	public void handleElemnt(WebDriver webDriver, TestStepData testStepData);
	public void matchExpectedRes(WebDriver webDriver, TestExpectedResult testExpectedResult) throws Exception; 
}
