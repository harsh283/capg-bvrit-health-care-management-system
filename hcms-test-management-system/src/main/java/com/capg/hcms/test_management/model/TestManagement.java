package com.capg.hcms.test_management.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestManagement {
	
	@Id
	private String testId;
	private String testName;
	
	public TestManagement(String testId, String testName) {
		super();
		this.testId = testId;
		this.testName = testName;
	}

	public TestManagement() {
		super();
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
		return "TestManagement [testId=" + testId + ", testName=" + testName + "]";
	}

	
}
