package com.sayanand.atf.framework.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sayanand.atf.framework.beans.Action;
import com.sayanand.atf.framework.beans.ElementType;
import com.sayanand.atf.framework.beans.TestDetails;
import com.sayanand.atf.framework.beans.TestExpectedResult;
import com.sayanand.atf.framework.beans.TestStep;
import com.sayanand.atf.framework.beans.TestStepData;
import com.sayanand.atf.framework.dao.TestDAO;

@Repository("testDAO")
public class TestDAOImpl implements TestDAO {

	private static final Logger logger = LoggerFactory.getLogger(TestDAOImpl.class); 
	
	private String SQL_GET_TEST_DETAILS = "select * from TEST_CASES where name = :name";
	private String SQL_GET_TEST_STEPS = "select steps.* from TEST_CASE_STEPS_MAPPING map, TEST_CASE_STEPS steps where steps.ID = map.TEST_STEP_ID and map.TEST_ID= :id order by map.ORD";
	private String SQL_GET_TEST_STEP_DATA = "select data.* from TEST_STEP_DATA_MAPPING map, TEST_STEP_DATA data where map.TEST_STEP_ID = :id and map.TEST_DATA_ID=data.ID order by map.ord";
	private String SQL_GET_TEST_EXPCT_RES = "select res.* from TEST_EXPECTED_RES_MAPPING map, TEST_EXPECTED_RES res where map.TEST_STEP_ID = :id and map.TEST_EXP_RES_ID = res.id order by map.ORD";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public TestDetails getTestDetails(String name) {
		logger.info("Getting details from db for {}", name);
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("name", name);
		TestDetails testDetails = this.jdbcTemplate.query(SQL_GET_TEST_DETAILS, paramMap, new ResultSetExtractor<TestDetails>() {
			public TestDetails extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				rs.next();
				TestDetails testDetails = new TestDetails();
				testDetails.setId(rs.getInt("ID"));
				testDetails.setName(rs.getString("NAME"));
				testDetails.setUrl(rs.getString("URL"));
				testDetails.setDescription(rs.getString("DESCRIPTION"));
				return testDetails;
			}			
		});	
		
		//get STeps
		List<TestStep> testSteps = getTestSteps(testDetails.getId());
		testDetails.setTestSteps(testSteps);		
		logger.debug("Got testDetails from db:{}", testDetails);
		return testDetails;
	}

	private List<TestStep> getTestSteps(int testId) {
		List<TestStep> res = new ArrayList<TestStep>();		
		logger.info("Getting test steps for {}", testId);
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", testId);
		
		res = this.jdbcTemplate.query(SQL_GET_TEST_STEPS, params, new RowMapper<TestStep>() {

			public TestStep mapRow(ResultSet rs, int arg1)
					throws SQLException {
				//rs.next();
				TestStep step = new TestStep();
				step.setId(rs.getInt("ID"));
				step.setName(rs.getString("NAME"));
				step.setDescription(rs.getString("DESCRIPTION"));
				
				List<TestStepData> testData = getTestStepData(step.getId());
				step.setTestData(testData);
				
				List<TestExpectedResult> exptRests = getTestStepResult(step.getId());
				step.setTestExpctRes(exptRests);				
				return step;
			}			
		});
		
		return res;
	}
	
	private List<TestStepData> getTestStepData(int testStepId) {
		logger.info("Getting test steps data for {}", testStepId);
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", testStepId);
		
		List<TestStepData> data = this.jdbcTemplate.query(SQL_GET_TEST_STEP_DATA, params, new RowMapper<TestStepData>() {

			public TestStepData mapRow(ResultSet rs, int arg1)
					throws SQLException {
				//rs.next();
				TestStepData data = new TestStepData();
				data.setId(rs.getInt("ID"));
				data.setName(rs.getString("ELEMENT_NAME"));
				data.setType(ElementType.valueOf(rs.getString("ELEMENT_TYPE")));
				data.setValue(rs.getString("VALUE"));
				data.setLocationXPath(rs.getString("LOCATION_XPATH"));
				data.setAction(Action.valueOf(rs.getString("ACTION")));				
				return data;
			}			
		});		
		logger.info("Got expected results for {} -> {}", testStepId, data.size());
		return data;
	}
	
	private List<TestExpectedResult> getTestStepResult(int testStepId) {
		logger.info("Getting test expected results for {}", testStepId);
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", testStepId);
		
		List<TestExpectedResult> testExctResList = this.jdbcTemplate.query(SQL_GET_TEST_EXPCT_RES, params, new RowMapper<TestExpectedResult>() {
			public TestExpectedResult mapRow(ResultSet rs, int arg1)
					throws SQLException {
				//rs.next();
				TestExpectedResult testRes = new TestExpectedResult();
				testRes.setId(rs.getInt("ID"));
				testRes.setName(rs.getString("ELEMENT_NAME"));
				testRes.setType(ElementType.valueOf(rs.getString("ELEMENT_TYPE")));
				testRes.setLocationXPath(rs.getString("LOCATION_XPATH"));
				testRes.setValue(rs.getString("VALUE"));
				return testRes;
			}			
		});
		logger.info("Got expected results for {} -> {}", testStepId, testExctResList.size());
		return testExctResList;
	}
}
