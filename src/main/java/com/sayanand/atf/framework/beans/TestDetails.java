package com.sayanand.atf.framework.beans;

import java.util.List;

public class TestDetails {
	private int id;
	private String name;
	private String description;
	
	private List<TestStep> testSteps;

	public List<TestStep> getTestSteps() {
		return testSteps;
	}

	public void setTestSteps(List<TestStep> testSteps) {
		this.testSteps = testSteps;
	}

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

	@Override
	public String toString() {
		return "TestDetails [id=" + id + ", name=" + name + ", description="
				+ description + ", testSteps=" + testSteps + "]";
	}
	
	
}
