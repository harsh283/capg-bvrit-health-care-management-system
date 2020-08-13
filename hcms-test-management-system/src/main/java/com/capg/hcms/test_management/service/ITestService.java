package com.capg.hcms.test_management.service;

import java.util.List;

import com.capg.hcms.test_management.exception.TestException;
import com.capg.hcms.test_management.model.TestManagement;
public interface ITestService {
	
	public TestManagement addTest(TestManagement test);
	
	public TestManagement deleteTestById(String testId) throws TestException;
	
	public List<TestManagement>  findAllTests() throws TestException;
	
	public TestManagement findTestById(String testId) throws TestException ;
	
	public TestManagement updateTest(TestManagement test)throws TestException ;
	
	
}
