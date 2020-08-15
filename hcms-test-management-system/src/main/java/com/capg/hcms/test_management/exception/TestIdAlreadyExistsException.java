package com.capg.hcms.test_management.exception;

public class TestIdAlreadyExistsException extends Exception {
	public TestIdAlreadyExistsException(String message)
	{
		super(message);
	}
	public TestIdAlreadyExistsException()
	{
		super();
	}
}

