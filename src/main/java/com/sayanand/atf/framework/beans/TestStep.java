package com.sayanand.atf.framework.beans;

import java.util.List;

public class TestStep {
	private int id;
	private String name;
	private String description;
	
	private List<TestStepData> testData;
	private List<TestExpectedResult> testExpctRes;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<TestStepData> getTestData() {
		return testData;
	}
	public void setTestData(List<TestStepData> testDate) {
		this.testData = testDate;
	}
	public List<TestExpectedResult> getTestExpctRes() {
		return testExpctRes;
	}
	public void setTestExpctRes(List<TestExpectedResult> testExpctRes) {
		this.testExpctRes = testExpctRes;
	}
	
	
}
