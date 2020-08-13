package com.capg.hcms.test_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hcms.test_management.exception.TestException;
import com.capg.hcms.test_management.model.TestManagement;
import com.capg.hcms.test_management.repo.TestRepo;

@Service
public class TestServiceImpl implements ITestService{

	@Autowired
	TestRepo testRepo;
	
	@Override
	public TestManagement addTest(TestManagement test) {
		TestManagement  addtest = testRepo.saveAndFlush(test);
	     return addtest;
	}

	@Override
	public TestManagement deleteTestById(String testId) throws TestException {
       TestManagement deletetest =null;
		if( testRepo.existsById(testId))
		{
			 deletetest = testRepo.findById(testId).get();
			 testRepo.deleteById(testId);
		}
		else
		{
			throw new TestException(" ID NOT FOUND ");
		}
		return deletetest;
	}

	@Override
	public List<TestManagement> findAllTests() throws TestException {
		List<TestManagement> listOfTests = testRepo.findAll();
		return listOfTests;
	}

	@Override
	public TestManagement findTestById(String testId) throws TestException {
		if( ! testRepo.existsById(testId))
		{
			throw new TestException(" ID NOT FOUND ");
		}
		return testRepo.findById(testId).get();
	}


	@Override
	public TestManagement updateTest(TestManagement test) throws TestException {
		String testId =test.getTestId();
		if(testRepo.existsById(testId))
		{
			TestManagement upadatetest=testRepo.findById(testId).get();
			testRepo.saveAndFlush(test);
		}
		else
		{
		throw new TestException("ID NOT FOUND");
		}
		return test;
	}

	
}
