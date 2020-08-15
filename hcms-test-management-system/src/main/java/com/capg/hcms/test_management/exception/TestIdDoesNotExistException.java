package com.capg.hcms.test_management.exception;

public class TestIdDoesNotExistException extends Exception {

	public TestIdDoesNotExistException(String message)
	{
		super(message);
	}
	public TestIdDoesNotExistException()
	{
		super();
	}
}
