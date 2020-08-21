package com.capg.hcms.appointmentmanagementsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.hcms.appointmentmanagementsystem.controller.ApproveAppointmentController;
import com.capg.hcms.appointmentmanagementsystem.repo.IAppointmentMSRepo;
import com.capg.hcms.appointmentmanagementsystem.service.AppointmentMSServiceImpl;

@SpringBootTest(classes = HcmsAppointmentManagementSystemApplication.class)
class HcmsApproveAppointmentMsApplicationTestsTest {

	@Autowired
	AppointmentMSServiceImpl service;
	@Autowired
	IAppointmentMSRepo repo;
	@Autowired
	ApproveAppointmentController approveAppointment;

	@Test
	void testGetAllAppointments() {
		assertEquals(true, !repo.findAll().isEmpty());
	}

}

	

