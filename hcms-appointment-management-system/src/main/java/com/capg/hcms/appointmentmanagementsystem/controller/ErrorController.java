package com.capg.hcms.appointmentmanagementsystem.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capg.hcms.appointmentmanagementsystem.Exception.AppointmentAlreadyApprovedException;
import com.capg.hcms.appointmentmanagementsystem.Exception.AppointmentNotFoundException;
import com.capg.hcms.appointmentmanagementsystem.Exception.ErrorInfo;
import com.capg.hcms.appointmentmanagementsystem.Exception.SlotNotAvailableException;



@RestControllerAdvice
public class ErrorController {
	@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(value= {AppointmentAlreadyApprovedException.class})
	public ErrorInfo handleAppointmentAlreadyApproved(AppointmentAlreadyApprovedException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}

	@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(value= {AppointmentNotFoundException.class})
	public ErrorInfo AppointmentNotFound(AppointmentNotFoundException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}
	@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(value= {SlotNotAvailableException.class})
	public ErrorInfo SlotNotAvailable(SlotNotAvailableException ex , HttpServletRequest req)
	{
		return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
	}
}
	