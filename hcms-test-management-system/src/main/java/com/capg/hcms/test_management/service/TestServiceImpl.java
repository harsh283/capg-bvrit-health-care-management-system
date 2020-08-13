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
		TestManagement  ts = testRepo.saveAndFlush(test);
	     return ts;
	}

	@Override
	public TestManagement deleteTestById(String testId) throws TestException {
       TestManagement ts =null;
		if( testRepo.existsById(testId))
		{
			 ts = testRepo.findById(testId).get();
			 testRepo.deleteById(testId);
		}
		else
		{
			throw new TestException(" ID NOT FOUND ");
		}
		return ts;
	}

	@Override
	public List<TestManagement> findAllTests() throws TestException {
		List<TestManagement> list = testRepo.findAll();
		return list;
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
			TestManagement t=testRepo.findById(testId).get();
			testRepo.saveAndFlush(test);
		}
		else
		{
		throw new TestException("ID NOT FOUND");
		}
		return test;
	}

	
}
