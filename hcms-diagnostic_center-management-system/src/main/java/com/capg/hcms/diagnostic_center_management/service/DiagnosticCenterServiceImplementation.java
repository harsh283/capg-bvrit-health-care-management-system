package com.capg.hcms.diagnostic_center_management.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hcms.diagnostic_center_management.exceptions.CenterAlreadyExistsException;
import com.capg.hcms.diagnostic_center_management.exceptions.NoCentersAreAvailableException;
import com.capg.hcms.diagnostic_center_management.exceptions.SpecifiedCenterDoesnotExistException;
import com.capg.hcms.diagnostic_center_management.model.DiagnosticCenter;
import com.capg.hcms.diagnostic_center_management.repo.DiagnosticCenterRepo;

@Service
public class DiagnosticCenterServiceImplementation implements IDiagnosticCenterService{
@Autowired
private Random random;
@Autowired
private DiagnosticCenterRepo repository;
@Override
public DiagnosticCenter getCenterById(String centerId) throws SpecifiedCenterDoesnotExistException {
	// TODO Auto-generated method stub
	boolean existingCenter = repository.existsById(centerId);
	if(existingCenter)
	{
		DiagnosticCenter center = repository.getOne(centerId);
		return center;
	}
	throw new SpecifiedCenterDoesnotExistException("Center with center id "+centerId+"Doesnot exist");
	
}
@Override
public List<DiagnosticCenter> getAllCenters() throws NoCentersAreAvailableException {
	// TODO Auto-generated method stub
	List<DiagnosticCenter> centerList=repository.findAll();
	if(centerList.isEmpty())
	{
		throw new NoCentersAreAvailableException("No center is present");
		
	}
	
		return centerList;
	
}


@Override
public DiagnosticCenter addCenter(DiagnosticCenter center) throws CenterAlreadyExistsException {
	// TODO Auto-generated method stub
	center.setCenterId(String.valueOf(random.nextInt(10000000)));
	if(repository.existsById(center.getCenterId()))
	{
		throw new CenterAlreadyExistsException("Center already exists kindly enter another center ID");
	}
	return repository.save(center);
	
}


@Override
public boolean removeAllCenters() throws NoCentersAreAvailableException {
	// TODO Auto-generated method stub
	List<DiagnosticCenter> centerList=repository.findAll();
	System.out.println(centerList);
	if(centerList.isEmpty())
	{
		
		throw new NoCentersAreAvailableException("Centers are not present");
	}
	repository.deleteAll();
	return true;
}

@Override
public boolean removeCenter(String centerId) throws NoCentersAreAvailableException {
	
	// TODO Auto-generated method stub
	if(repository.existsById(centerId))
	{
		repository.deleteById(centerId);
		return true;
	}

	else {
		throw new NoCentersAreAvailableException("Center doesnot exist kindly enter another center ID");
	}
}
@Override
public DiagnosticCenter addTestId(String centerId, String testId) throws CenterAlreadyExistsException {
	// TODO Auto-generated method stub
	DiagnosticCenter existingCenter=repository.getOne(centerId);
	if(existingCenter==null)
	{
		throw new CenterAlreadyExistsException("Center with specified centerId unavailable");
	}
	existingCenter.getTests().add(testId);
	

	return repository.save(existingCenter);
}
@Override
public DiagnosticCenter addAppointmentId(String centerId, BigInteger appointmentId) {
	// TODO Auto-generated method stub
	DiagnosticCenter existingCenter=repository.getOne(centerId);
	existingCenter.getAppointments().add(appointmentId);
	return repository.save(existingCenter);
}
@Override
public boolean removeTestId(String centerId, String testId) {
	// TODO Auto-generated method stub
	DiagnosticCenter center=repository.getOne(centerId);
	
	center.getTests().remove(testId);
	
	repository.save(center);
	
	return true;

}
@Override
public List<DiagnosticCenter> setTestsToNull() {
	// TODO Auto-generated method stub
	List<DiagnosticCenter> centerList=repository.findAll();
	for (DiagnosticCenter diagnosticCenter : centerList) {
		diagnosticCenter.setTests(null);
		repository.save(diagnosticCenter);
	}
	List<DiagnosticCenter> nullTestList=repository.findAll();
	
	return nullTestList;
}

}
