package com.sayanand.atf.framework.beans;

public class TestExpectedResult {

	private int id;
	private String name;
	private ElementType type;
	private String locationXPath;
	private String value;
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
	public String getLocationXPath() {
		return locationXPath;
	}
	public void setLocationXPath(String locationXPath) {
		this.locationXPath = locationXPath;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
