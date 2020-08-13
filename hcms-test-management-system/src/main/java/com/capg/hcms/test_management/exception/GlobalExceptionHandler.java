package com.capg.hcms.test_management.exception;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TestException.class)
	public @ResponseBody ResponseEntity<ErrorInfo>  handleException(TestException exception,HttpServletRequest request)
	{
		
		 String message=exception.getMessage();
		 String uri= request.getRequestURI();
		 
		 ErrorInfo  errorinfo = new ErrorInfo(LocalDateTime.now(),message,uri);
		 ResponseEntity<ErrorInfo>  responseentity = new ResponseEntity<ErrorInfo>(errorinfo,HttpStatus.NOT_FOUND);
		 return responseentity;
	}
	
	
}
