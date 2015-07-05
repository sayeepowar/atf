/**
 * 
 */
package com.sayanand.atf.framework.impl;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sayanand.atf.framework.TestRun;
import com.sayanand.atf.framework.beans.ElementType;
import com.sayanand.atf.framework.beans.TestDetails;
import com.sayanand.atf.framework.beans.TestExpectedResult;
import com.sayanand.atf.framework.beans.TestStep;
import com.sayanand.atf.framework.beans.TestStepData;
import com.sayanand.atf.framework.dao.TestDAO;
import com.sayanand.atf.framework.handler.ElementHandler;

/**
 * @author Nandkishore.Powar
 *
 */
@Component("testRun")
public class TestRunImpl implements TestRun {

	private static final Logger logger =LoggerFactory.getLogger(TestRunImpl.class);
	
	@Autowired
	private TestDAO testDAO;
	
	@Autowired 
	ElementHandler textboxHandler;
	
	@Autowired 
	ElementHandler buttonHandler;
	
	@Autowired
	private Map<String, ElementHandler> elementHandlerMap;
	
	/* (non-Javadoc)
	 * @see com.sayanand.atf.framework.TestRun#executeTest()
	 */
	public void executeTest(String testName) throws Exception {
		logger.info("Executin testName : {} ", testName);
		
		// get test details from db
		TestDetails testDetails = testDAO.getTestDetails(testName);
		
		//launch application
		String url = testDetails.getUrl();
		logger.debug("Launcing test url -> {}", url);
		
		WebDriver webDriver = new FirefoxDriver();		
		webDriver.get(url);
				
		// run through each step
		for ( TestStep testStep : testDetails.getTestSteps() ) {
			logger.info("executing step : {}", testStep.getName());			
			// input data
			if ( testStep.getTestData()!=null ) {
				for ( TestStepData testStepData : testStep.getTestData() ) {
					logger.info("Applying test data - {}-{}", testStepData.getName(), testStepData.getAction());
					ElementType type = testStepData.getType();
					switch (type) {
					case INPUT_TEXT:
						textboxHandler.handleElemnt(webDriver, testStepData);
						break;
					case BUTTON :
						buttonHandler.handleElemnt(webDriver, testStepData);
					default:
				 		break;
					}
					//ElementHandler handler = elementHandlerMap.get(type.name());
					//handler.handleElemnt(webDriver, testStepData);
				}
			}			
			// match expected results
			if( testStep.getTestExpctRes()!=null) {
				for ( TestExpectedResult exptRes : testStep.getTestExpctRes() ) {
					logger.info("Checking expected result {}-{}", exptRes.getName(), exptRes.getValue());
					switch (exptRes.getType()) {
					case INPUT_TEXT:
						textboxHandler.matchExpectedRes(webDriver, exptRes);
						break;
					default:
						break;
					}					
					//ElementHandler handler = elementHandlerMap.get(exptRes.getType().name());
					//handler.matchExpectedRes(webDriver, exptRes);
				}
			}
		}
	}

}
