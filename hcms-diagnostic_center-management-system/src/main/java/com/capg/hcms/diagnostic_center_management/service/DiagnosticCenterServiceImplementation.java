package com.capg.hcms.diagnostic_center_management.service;

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
	DiagnosticCenter existingCenter=repository.getOne(centerId);
	if(existingCenter==null)
	{
		throw new SpecifiedCenterDoesnotExistException("Center with center id "+centerId+"Doesnot exist");
	}
	return existingCenter;
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
}
