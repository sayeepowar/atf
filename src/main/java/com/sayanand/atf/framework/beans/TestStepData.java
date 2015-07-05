package com.sayanand.atf.framework.beans;

public class TestStepData {

	private int id;
	private String name;
	private ElementType type;
	private String value;
	private String locationXPath;
	private Action action;
	
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
	public ElementType getType() {
		return type;
	}
	public void setType(ElementType type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLocationXPath() {
		return locationXPath;
	}
	public void setLocationXPath(String locationXPath) {
		this.locationXPath = locationXPath;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}	
	
	
}
