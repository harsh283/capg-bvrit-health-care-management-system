package com.capg.hcms.appointmentmanagementsystem.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hcms.appointmentmanagementsystem.Exception.AppointmentAlreadyApprovedException;
import com.capg.hcms.appointmentmanagementsystem.Exception.AppointmentNotFoundException;
import com.capg.hcms.appointmentmanagementsystem.Exception.SlotNotAvailableException;
import com.capg.hcms.appointmentmanagementsystem.model.Appointment;
import com.capg.hcms.appointmentmanagementsystem.model.AppointmentList;
import com.capg.hcms.appointmentmanagementsystem.repo.IAppointmentMSRepo;

@Service
public class AppointmentMSServiceImpl implements IAppointmentMSService {

	@Autowired
	IAppointmentMSRepo appointmentRepo;
	
	

	@Override
	public Appointment makeAppointment(Appointment appointment) {
		
		LocalTime time=appointment.getDateTime().toLocalTime();

		
	    if ((appointmentRepo.getAppointmentByDateTimeAndTestId(appointment.getDateTime(), appointment.getTestId())!=null)
	    	||appointment.getDateTime().isBefore(LocalDateTime.now().plusHours(1))||appointment.getDateTime().isAfter(LocalDateTime.now().plusMonths(3))
	    	||time.isBefore(LocalTime.of(6, 59))||time.isAfter(LocalTime.of(21, 00))) 
	    {
			throw new SlotNotAvailableException("This slot is not available");
		}
		return appointmentRepo.save(appointment);
	}

	@Override
	public Appointment getAppointment(BigInteger appointmentId) {

		if (!appointmentRepo.existsById(appointmentId)) {
			throw new AppointmentNotFoundException("Appointment with id " + appointmentId + "not found");
		}
		return appointmentRepo.getOne(appointmentId);
	}


	@Override
	public AppointmentList getAllAppointments() {

		if (appointmentRepo.findAll().isEmpty()) {
			throw new AppointmentNotFoundException("Appointment list is empty");
		}
		return new AppointmentList(appointmentRepo.findAll());
	}
	
	
    @Override
	public Appointment approveAppointment(Appointment appointment, boolean status) {

		if (appointment.isApproved()) {
			throw new AppointmentAlreadyApprovedException(
					"Appointment with Id :" + appointment.getAppointmentId() + " is Already Approved");
		}

		appointment.setApproved(status);
		return appointmentRepo.save(appointment);
	}
    
    @Override
	public boolean removeAppointmentById(BigInteger appointmentId) {
    	
    	if(!appointmentRepo.existsById(appointmentId)) {
    		throw new AppointmentNotFoundException("Appointment with id: "+appointmentId+" not found");
    	}
		appointmentRepo.deleteById(appointmentId);
		return true;
	}

	
	

	

}
