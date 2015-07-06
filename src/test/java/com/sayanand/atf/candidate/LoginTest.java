package com.sayanand.atf.candidate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Component;

import com.sayanand.atf.framework.TestRun;
import com.sayanand.atf.framework.util.ATFContext;

@Component
public class LoginTest {

	
	private TestRun testRun;
	
	@Before
	public void setup(){
		testRun = (TestRun)ATFContext.getBean("testRun");
	}
	
	@Test
	public void testLogin() {
		try {
			testRun.executeTest("CANDIDATE LOGIN");
		}catch ( Exception e) {
			e.printStackTrace();
			Assert.fail("Failed Candidate Login. Cause:"+e.getMessage());
		}
	}
}
