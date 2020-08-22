package com.capg.hcms.appointmentmanagementsystem.repo;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.hcms.appointmentmanagementsystem.model.Appointment;

/*******************************************************************************************************************************
-Author                   :     Ravali Bondugula
-Created/Modified Date    :     
-Description              :     AppointmentMsRepo interface acting as repository with Appointment class a type and BigInteger as ID
*******************************************************************************************************************************/


public interface IAppointmentMSRepo extends JpaRepository<Appointment, BigInteger> {
  
  public Appointment getAppointmentByDateTimeAndTestId(LocalDateTime dateTime,String testId);

 
  
}

