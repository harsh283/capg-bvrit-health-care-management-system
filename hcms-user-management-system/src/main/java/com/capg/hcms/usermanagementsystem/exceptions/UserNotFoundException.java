package com.capg.hcms.usermanagementsystem.exceptions;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message)
	{
		super(message);
	}
	public UserNotFoundException()
	{
		super();
	}
}
