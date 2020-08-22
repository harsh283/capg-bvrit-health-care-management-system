package com.capg.hcms.appointmentmanagementsystem.Exception;

/*******************************************************************************************************************************
-Author                   :     Ravali Bondugula
-Created/Modified Date    :    
-Description              :     AppointmentAlreadyApprovedException class to handle runtime exception
*******************************************************************************************************************************/


public class AppointmentAlreadyApprovedException extends RuntimeException {

	public AppointmentAlreadyApprovedException(String message)
	{
		super(message);
	}
}
