package com.capg.hcms.appointmentmanagementsystem.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hcms.appointmentmanagementsystem.Exception.AppointmentAlreadyApprovedException;
import com.capg.hcms.appointmentmanagementsystem.Exception.AppointmentNotFoundException;
import com.capg.hcms.appointmentmanagementsystem.Exception.SlotNotAvailableException;
import com.capg.hcms.appointmentmanagementsystem.model.Appointment;
import com.capg.hcms.appointmentmanagementsystem.repo.IAppointmentMSRepo;

/*******************************************************************************************************************************
-Author                   :     Ravali Bondugula
-Created/Modified Date    :     
-Description              :     AppointmentMSServiceImp Class implements services for Appointment Management System
*******************************************************************************************************************************/


@Service
public class AppointmentMSServiceImpl implements IAppointmentMSService {

	@Autowired
	IAppointmentMSRepo appointmentRepo;
	
	/*******************************************************************************************************************************
	-Function Name            :     makeAppointment
	-Input Parameters         :     Appointment Object
	-Return Type              :     appointment object
	-Throws                   :     SlotNotAvailableException
	-Author                   :     Ravali Bondugula
	-Created/Modified Date    :     
	-Description              :     adding appointment to the appointment database table 
	*******************************************************************************************************************************/
	
	
	@Override
	public Appointment makeAppointment(Appointment appointment) {
	
		LocalTime time=appointment.getDateTime().toLocalTime();

		
	    if ((appointmentRepo.getAppointmentByDateTimeAndTestId(appointment.getDateTime(),
	    		appointment.getTestId())!=null))
	
	    	//	||appointment.getDateTime().isBefore(LocalDateTime.now().plusHours(1))
	    	//	||appointment.getDateTime().isAfter(LocalDateTime.now().plusMonths(3))
	    	//	||time.isBefore(LocalTime.of(6, 59))||time.isAfter(LocalTime.of(21, 00))) 
	    {
			throw new SlotNotAvailableException("This slot is not available");
		}
	   // System.out.println(appointment);
		Appointment app=appointmentRepo.save(appointment);
	return app;
	}
	
	/*******************************************************************************************************************************
	-Function Name            :     getAppointment
	-Input Parameters         :     BigInteger appointmentId 
	-Return Type              :     Appointment Object
	-Throws                   :     AppointmentNotFoundException
	-Author                   :     Ravali Bondugula
	-Created/Modified Date    :     
	-Description              :     getting appointment based on appointmentId from appointment database table
	*******************************************************************************************************************************/


	@Override
	public Appointment getAppointment(BigInteger appointmentId) {

		if (!appointmentRepo.existsById(appointmentId)) {
			throw new AppointmentNotFoundException("Appointment with id " + appointmentId + "not found");
		}
		return appointmentRepo.getOne(appointmentId);
	}
	
	/*******************************************************************************************************************************
	-Function Name            :     getAllAppointments
	-Input Parameters         :     none
	-Return Type              :     appointment list
	-Throws                   :     AppointmentNotFoundException
	-Author                   :     Ravali Bondugula
	-Created/Modified Date    :     
	-Description              :     getting all the appointments from the appointment database table 
	*******************************************************************************************************************************/
    
	@Override
	public List<Appointment> getAllAppointments() {

		if (appointmentRepo.findAll().isEmpty()) {
			throw new AppointmentNotFoundException("Appointment list is empty");
		}
		List<Appointment> allAppointments = appointmentRepo.findAll();
		return allAppointments;
	}
	
	/*******************************************************************************************************************************
	-Function Name            :     approveAppointment
	-Input Parameters         :     Appointment Object and status boolean variable 
	-Return Type              :     appointment object
	-Throws                   :     AppointmentAlreadyApprovedException
	-Author                   :     Ravali Bondugula
	-Created/Modified Date    :  
	-Description              :     approves appointment and updates the appointment present in appointment database table 
	*******************************************************************************************************************************/

	@Override
	public Appointment approveAppointment(Appointment appointment, boolean status) {

		if (appointment.isApproved()) {
			throw new AppointmentAlreadyApprovedException(
					"Appointment with Id :" + appointment.getAppointmentId() + " is Already Approved");
		}

		appointment.setApproved(status);
		return appointmentRepo.save(appointment);
	}
	
	/*******************************************************************************************************************************
	-Function Name            :     removeAppointmentById
	-Input Parameters         :     BigInteger appointmentId  
	-Return Type              :     boolean status
	-Throws                   :     AppointmentAlreadyApprovedException
	-Author                   :     Ravali Bondugula
	-Created/Modified Date    :     
	-Description              :     deletes appointment that is stored under listOfAppointments in a Diagnostic center
	*******************************************************************************************************************************/
    
    @Override
	public boolean removeAppointmentById(BigInteger appointmentId) {
    	
    	if(!appointmentRepo.existsById(appointmentId)) {
    		throw new AppointmentNotFoundException("Appointment with id: "+appointmentId+" not found");
    	}
		appointmentRepo.deleteById(appointmentId);
		return true;
	}
    
}
