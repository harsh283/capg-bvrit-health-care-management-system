package com.capg.hcms.usermanagementsystem.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capg.hcms.usermanagementsystem.exceptions.ContactNumberAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.EmailAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.ErrorInfo;
import com.capg.hcms.usermanagementsystem.exceptions.UserEmailInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNotFoundException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNumberInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserPasswordInvalidException;

@RestControllerAdvice
public class ErrorController {
	@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(value= {UserNameInvalidException.class})
	public ErrorInfo handleUserNameInvalid(UserNameInvalidException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}

	@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(value= {UserEmailInvalidException.class})
	public ErrorInfo handleEmailInvalid(UserEmailInvalidException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}
	@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(value= {UserPasswordInvalidException.class})
	public ErrorInfo handlePasswordInvalid(UserPasswordInvalidException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}
	@ResponseStatus(code=HttpStatus.FORBIDDEN)
	@ExceptionHandler(value= {ContactNumberAlreadyExistException.class})
	public ErrorInfo handleContactNumberExist(ContactNumberAlreadyExistException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}
	@ResponseStatus(code=HttpStatus.FORBIDDEN)
	@ExceptionHandler(value= {EmailAlreadyExistException.class})
	public ErrorInfo handleEmailExistException(EmailAlreadyExistException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}
	@ResponseStatus(code=HttpStatus.FORBIDDEN)
	@ExceptionHandler(value= {UserNameAlreadyExistException.class})
	public ErrorInfo handlePasswordInvalid(UserNameAlreadyExistException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value= {UserNotFoundException.class})
	public ErrorInfo handleUserNotFound(UserNotFoundException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value= {UserNumberInvalidException.class})
	public ErrorInfo handleUserNumberInvalid(UserNumberInvalidException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}
	
}
