package com.capg.hcms.appointmentmanagementsystem.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.hcms.appointmentmanagementsystem.model.Appointment;
import com.capg.hcms.appointmentmanagementsystem.service.IAppointmentMSService;

/*******************************************************************************************************************************
-Author                   :     Ravali Bondugula
-Created/Modified Date    :     
-Description              :     ApproveAppointmentController Class for accessing Appointment Management System services
*******************************************************************************************************************************/

@RestController
@RequestMapping("/appointmentadmin")
public class ApproveAppointmentController {

	@Autowired
	IAppointmentMSService service;

	@GetMapping("/getAppointment/{appointmentId}")
	public Appointment getAppointment(@PathVariable BigInteger appointmentId) {
		return service.getAppointment(appointmentId);
	}

	@GetMapping("/getallappointments")
	public List<Appointment> getAllAppointment() {
		return service.getAllAppointments();
	}

	@PutMapping("/approveAppointment/{appointmentId}/status/{status}")
	public Appointment approveAppoinment(@PathVariable BigInteger appointmentId, @PathVariable boolean status) {
		Appointment appointment = service.getAppointment(appointmentId);
		return service.approveAppointment(appointment, status);
	}

}