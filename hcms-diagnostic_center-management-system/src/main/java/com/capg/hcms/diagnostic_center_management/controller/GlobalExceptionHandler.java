package com.capg.hcms.diagnostic_center_management.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capg.hcms.diagnostic_center_management.exceptions.CenterAlreadyExistsException;
import com.capg.hcms.diagnostic_center_management.exceptions.ErrorInfo;
import com.capg.hcms.diagnostic_center_management.exceptions.NoCentersAreAvailableException;
import com.capg.hcms.diagnostic_center_management.exceptions.SpecifiedCenterDoesnotExistException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler(value= {NoCentersAreAvailableException.class})
	public ErrorInfo handleTraineeAlreadyExistsException(NoCentersAreAvailableException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler(value= {SpecifiedCenterDoesnotExistException.class})
	public ErrorInfo handleSpecifiedCenterDoesnotExistException(SpecifiedCenterDoesnotExistException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value= {CenterAlreadyExistsException.class})
	public ErrorInfo handleCenterAlreadyExistsException(CenterAlreadyExistsException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}

}
