/**
	* Project Name : Health Care Management System
	* 
**/
package com.capg.hcms.diagnostic_center_management.service;
import java.math.BigInteger;
import java.util.List;

import com.capg.hcms.diagnostic_center_management.exceptions.CenterAlreadyExistsException;
import com.capg.hcms.diagnostic_center_management.exceptions.CenterNameAlreadyExistsException;
import com.capg.hcms.diagnostic_center_management.exceptions.NoCentersAreAvailableException;
import com.capg.hcms.diagnostic_center_management.exceptions.SpecifiedCenterDoesnotExistException;
import com.capg.hcms.diagnostic_center_management.model.DiagnosticCenter;

/**
* DiagnosticCenterService Interface with services for DiagnosticCenter Management System
* @author   :Shambu Harsh Kumar
* @version  :1.0
* @since    :2020-08-13 
**/
	public interface IDiagnosticCenterService {
		
		DiagnosticCenter addCenter(DiagnosticCenter center) throws  NoCentersAreAvailableException, CenterNameAlreadyExistsException, CenterAlreadyExistsException;
		
		List<DiagnosticCenter> getAllCenters() throws NoCentersAreAvailableException;
		
		DiagnosticCenter getCenterById(String centerId) throws SpecifiedCenterDoesnotExistException;
		
		boolean removeAllCenters() throws NoCentersAreAvailableException;
		
		boolean removeCenter(String centerId) throws SpecifiedCenterDoesnotExistException;
		
		DiagnosticCenter addTestId(String centerId,String testId) throws  SpecifiedCenterDoesnotExistException;
		
		DiagnosticCenter addAppointmentId(String centerId,BigInteger appointmentId);
		
		boolean removeTestId(String centerId,String testId);
		
		List<DiagnosticCenter> setTestsToNull();
		
		
}
