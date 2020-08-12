package com.capg.hcms.appointmentmanagementsystem.repo;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.hcms.appointmentmanagementsystem.model.Appointment;

public interface IAppointmentMSRepo extends JpaRepository<Appointment,BigInteger> {

}
