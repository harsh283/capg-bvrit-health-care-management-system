package com.capg.hcms.test_management.service;

import java.util.List;

import com.capg.hcms.test_management.exception.NoTestIsAvailableException;
import com.capg.hcms.test_management.exception.TestIdAlreadyExistsException;
import com.capg.hcms.test_management.exception.TestIdDoesNotExistException;
import com.capg.hcms.test_management.model.TestManagement;
public interface ITestService {
	
	public TestManagement addTest(TestManagement test)throws TestIdAlreadyExistsException;
	
	public boolean deleteTestById(String testId) throws TestIdDoesNotExistException;
	
	public boolean deleteAllTests()throws NoTestIsAvailableException ;
	
	public List<TestManagement>  findAllTests() throws NoTestIsAvailableException;
	
	public TestManagement findTestById(String testId) throws TestIdDoesNotExistException;
	
	public TestManagement updateTest(TestManagement test)throws TestIdDoesNotExistException;

	public List<TestManagement> addDefault();
	
}
