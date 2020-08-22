package com.capg.hcms.diagnostic_center_management.service;

import java.math.BigInteger;
import java.util.List;

import com.capg.hcms.diagnostic_center_management.exceptions.CenterAlreadyExistsException;
import com.capg.hcms.diagnostic_center_management.exceptions.CenterNameAlreadyExistsException;
import com.capg.hcms.diagnostic_center_management.exceptions.NoCentersAreAvailableException;
import com.capg.hcms.diagnostic_center_management.exceptions.SpecifiedCenterDoesnotExistException;
import com.capg.hcms.diagnostic_center_management.model.DiagnosticCenter;

public interface IDiagnosticCenterService {
DiagnosticCenter addCenter(DiagnosticCenter center) throws  NoCentersAreAvailableException, CenterNameAlreadyExistsException, CenterAlreadyExistsException;
List<DiagnosticCenter> getAllCenters() throws NoCentersAreAvailableException;
DiagnosticCenter getCenterById(String centerId) throws SpecifiedCenterDoesnotExistException;
boolean removeAllCenters() throws NoCentersAreAvailableException;
boolean removeCenter(String centerId) throws NoCentersAreAvailableException;
DiagnosticCenter addTestId(String centerId,String testId) throws CenterAlreadyExistsException;
DiagnosticCenter addAppointmentId(String centerId,BigInteger appointmentId);
boolean removeTestId(String centerId,String testId);
List<DiagnosticCenter> setTestsToNull();
}
