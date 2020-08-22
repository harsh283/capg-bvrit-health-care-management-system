package com.capg.hcms.usermanagementsystem.exceptions;

public class UserPasswordInvalidException extends RuntimeException {
    public UserPasswordInvalidException(String message)
    {
    	super(message);
    }
}
