package com.capg.hcms.appointmentmanagementsystem.service;


import java.math.BigInteger;
import java.util.List;

import com.capg.hcms.appointmentmanagementsystem.model.Appointment;
import com.capg.hcms.appointmentmanagementsystem.model.AppointmentList;


public interface IAppointmentMSService {

	Appointment makeAppointment(Appointment appointment);
    Appointment getAppointment(BigInteger appointmentId);
	List<Appointment> getAllAppointments();
	Appointment approveAppointment(Appointment appointment,boolean status);
	boolean removeAppointmentById(BigInteger appointmentId);
	
}
