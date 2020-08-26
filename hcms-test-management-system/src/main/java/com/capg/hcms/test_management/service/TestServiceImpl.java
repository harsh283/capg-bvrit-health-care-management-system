package com.capg.hcms.test_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hcms.test_management.exception.NoTestIsAvailableException;
import com.capg.hcms.test_management.exception.TestIdAlreadyExistsException;
import com.capg.hcms.test_management.exception.TestIdDoesNotExistException;
import com.capg.hcms.test_management.model.TestManagement;
import com.capg.hcms.test_management.repo.TestRepo;

@Service
public class TestServiceImpl implements ITestService{

	@Autowired
	TestRepo testRepo;
	@Autowired
	private Random random;
	
	@Override
	public TestManagement addTest(TestManagement test) throws TestIdAlreadyExistsException {
		if(testRepo.existsById(test.getTestId()))
		{
		throw new TestIdAlreadyExistsException("Test with testId" +test.getTestId()+" alreadyExists");	
		}
		TestManagement  addtest = testRepo.save(test);
	     return addtest;
	}

	@Override
	public boolean deleteTestById(String testId) throws TestIdDoesNotExistException {
       TestManagement deletetest =null;
		if( testRepo.existsById(testId))
		{
			 deletetest = testRepo.findById(testId).get();
			 testRepo.deleteById(testId);
			 return true;
		}
		else
		{
			throw new TestIdDoesNotExistException ("TestId does Not Exists");
		}
		
	}

	@Override
	public List<TestManagement> findAllTests() throws NoTestIsAvailableException {
		List<TestManagement> listOfTests = testRepo.findAll();
			System.out.println(listOfTests);
		if(listOfTests.isEmpty())
		{
			
			throw new NoTestIsAvailableException("No Test Is Present");
		}
		testRepo.findAll();
		return listOfTests;
	}
	

	@Override
	public TestManagement findTestById(String testId) throws TestIdDoesNotExistException {
		if( ! testRepo.existsById(testId))
		{
			throw new TestIdDoesNotExistException("TestId does Not Exists");
		}
		return testRepo.findById(testId).get();
	}


	@Override
	public TestManagement updateTest(TestManagement test) throws TestIdDoesNotExistException {
		String testId =test.getTestId();
		if(testRepo.existsById(testId))
		{
			TestManagement upadateTest=testRepo.findById(testId).get();
			testRepo.saveAndFlush(test);
		}
		else
		{
		throw new TestIdDoesNotExistException("Test with testId" +test.getTestId()+" doesNotExists");
		}
		return test;
	}

	@Override
	public boolean deleteAllTests() throws NoTestIsAvailableException {
		List<TestManagement> listOfTests=testRepo.findAll();
		System.out.println(listOfTests);
		if(listOfTests.isEmpty())
		{
			
			throw new NoTestIsAvailableException("No Test Is Present");
		}
		testRepo.deleteAll();
		return true;
	}

	@Override
	public List<TestManagement> addDefault() {

		TestManagement test1=new TestManagement(String.valueOf(random.nextInt(1000)),"Blood Pressure");
		TestManagement test2=new TestManagement(String.valueOf(random.nextInt(1000)),"Blood Sugar");
		
		List<TestManagement> testList=new ArrayList();
		testList.add(test1);
		testList.add(test2);
		testRepo.save(test1);
		testRepo.save(test2);
		return testList;
	}
	
	
}
