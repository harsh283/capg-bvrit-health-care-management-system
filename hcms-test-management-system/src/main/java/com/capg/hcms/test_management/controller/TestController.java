package com.capg.hcms.test_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.capg.hcms.test_management.exception.TestException;
import com.capg.hcms.test_management.model.TestManagement;
import com.capg.hcms.test_management.service.ITestService;


@RestController
public class TestController {

	@Autowired
	ITestService  testService;

	@GetMapping("/getalltest")
	public ResponseEntity<List<TestManagement>>  findAllTests() throws TestException
	{
	  
		List<TestManagement> list=testService.findAllTests();
		ResponseEntity<List<TestManagement>>  listOfTests = new ResponseEntity<List<TestManagement>>(list,HttpStatus.OK);
		return listOfTests;
		
	}	
	@PostMapping("/addtest")
	public ResponseEntity<TestManagement>  addTest(@RequestBody TestManagement test)
	{
		TestManagement te=testService.addTest(test);
		ResponseEntity<TestManagement> ResponseEntityadd  = new ResponseEntity<TestManagement>(te,HttpStatus.OK);
		return ResponseEntityadd;
	}
	
	
	
	@DeleteMapping("deletetest/id/{id}")
	public  ResponseEntity<TestManagement>  deleteTestById(@PathVariable("id") String testId) throws TestException
	{
		
		ResponseEntity<TestManagement>  ResponseEntitydelete = null;
	    TestManagement test = testService.deleteTestById(testId);
	    ResponseEntitydelete= new ResponseEntity<TestManagement>(test,HttpStatus.OK);
		return ResponseEntitydelete;
	}
	@GetMapping("gettest/id/{id}")
	public  ResponseEntity<TestManagement>  findTestById(@PathVariable("id")  String id) throws TestException 
	{
		
		   TestManagement  test = testService.findTestById(id);
		   ResponseEntity<TestManagement>  testbyid= new ResponseEntity<TestManagement>(test,HttpStatus.OK);
		   return testbyid;
	}
	
	
	@PutMapping("/updatetest")
	public ResponseEntity<TestManagement> updateTest(@RequestBody TestManagement test) throws TestException
	{
		ResponseEntity<TestManagement> ResponseEntityupdate =null;
		if(test!=null)
		{
		test=testService.updateTest(test);
		ResponseEntityupdate=new ResponseEntity<TestManagement>(test,HttpStatus.OK);
		}
		else
		{
			ResponseEntityupdate=new ResponseEntity<TestManagement>(test,HttpStatus.NOT_FOUND);
		}
		return ResponseEntityupdate;
	}
}