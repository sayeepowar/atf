/**
 * 
 */
package com.sayanand.atf.framework.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sayanand.atf.framework.TestRun;
import com.sayanand.atf.framework.beans.TestDetails;
import com.sayanand.atf.framework.dao.TestDAO;

/**
 * @author Nandkishore.Powar
 *
 */
public class TestRunImpl implements TestRun {

	private static final Logger logger =LoggerFactory.getLogger(TestRunImpl.class);
	
	@Autowired
	private TestDAO testDAO;
	
	/* (non-Javadoc)
	 * @see com.sayanand.atf.framework.TestRun#executeTest()
	 */
	public void executeTest(String testName) {
		logger.info("Executin testName : {} ", testName);
		
		// get test details from db
		TestDetails testDetails = testDAO.getTestDetails(testName);
		
		// launch url
		
		// input data
		
		// fire tests
		
		// match expected results
		
		
		

	}

}
