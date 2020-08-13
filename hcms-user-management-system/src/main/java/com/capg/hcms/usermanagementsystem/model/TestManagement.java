package com.capg.hcms.usermanagementsystem.model;

public class TestManagement {

	private String testId;
	private String testName;
	
	public TestManagement() {
		super();
		
	}
	public TestManagement(String testId, String testName) {
		super();
		this.testId = testId;
		this.testName = testName;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	@Override
	public String toString() {
		return "Test [testId=" + testId + ", testName=" + testName + "]";
	}
	}
