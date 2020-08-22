package com.capg.hcms.appointmentmanagementsystem;

import static org.junit.jupiter.api.Assertions.assertThrows;


import java.math.BigInteger;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.hcms.appointmentmanagementsystem.Exception.AppointmentNotFoundException;
import com.capg.hcms.appointmentmanagementsystem.Exception.SlotNotAvailableException;
import com.capg.hcms.appointmentmanagementsystem.model.Appointment;
import com.capg.hcms.appointmentmanagementsystem.service.IAppointmentMSService;

@SpringBootTest(classes = HcmsAppointmentManagementSystemApplication.class)
class HcmsAppointmentMsApplicationTests {

	@Autowired
	IAppointmentMSService service;
	
	Appointment appointment;
	Appointment appointment1;
	Appointment appointment2;
	

	
	@BeforeEach
	public void setUp() {

		appointment = new Appointment("120", BigInteger.valueOf(0), "23", LocalDateTime.of(2020, 6, 20, 9, 30), false);
		appointment1=new Appointment("230", BigInteger.valueOf(0), "12", LocalDateTime.of(2020, 5, 9, 9, 30), false);
		appointment2=new Appointment("230", BigInteger.valueOf(0) , "12", LocalDateTime.of(2020,9 , 9, 11, 30), false);
	}

	//@Test
	//public void testMakeAppointment() {

		//Appointment newAppointment=service.makeAppointment(appointment);
		//assertEquals(true,service.getAppointment(newAppointment.getAppointmentId())!=null);
		

	//}

	@Test
	public void testSlotNotAvailableException() {

		assertThrows(SlotNotAvailableException.class, () -> {service.makeAppointment(appointment);});
		assertThrows(SlotNotAvailableException.class, ()->{service.makeAppointment(appointment1);});
		assertThrows(SlotNotAvailableException.class, ()->{service.makeAppointment(appointment2);});

	}
	

	@Test
	public void testAppointmentNotFoundException() {
		assertThrows(AppointmentNotFoundException.class, () -> {
			service.getAppointment(BigInteger.valueOf(50));
		});
	}

}
