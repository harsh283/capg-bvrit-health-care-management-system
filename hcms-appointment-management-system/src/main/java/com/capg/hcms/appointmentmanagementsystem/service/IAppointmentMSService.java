package com.capg.hcms.appointmentmanagementsystem.service;


import java.math.BigInteger;


import com.capg.hcms.appointmentmanagementsystem.model.Appointment;
import com.capg.hcms.appointmentmanagementsystem.model.AppointmentList;


public interface IAppointmentMSService {

	Appointment makeAppointment(Appointment appointment);
    Appointment getAppointment(BigInteger appointmentId);
	AppointmentList getAllAppointments();
	Appointment approveAppointment(Appointment appointment,boolean status);
	boolean removeAppointmentById(BigInteger appointmentId);
	
}
