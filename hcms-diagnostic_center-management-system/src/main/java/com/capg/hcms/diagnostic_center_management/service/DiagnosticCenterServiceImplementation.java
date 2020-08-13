package com.capg.hcms.diagnostic_center_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hcms.diagnostic_center_management.model.DiagnosticCenter;
import com.capg.hcms.diagnostic_center_management.repo.DiagnosticCenterRepo;

@Service
public class DiagnosticCenterServiceImplementation implements IDiagnosticCenterService{
@Autowired
private DiagnosticCenterRepo repository;

@Override
public DiagnosticCenter addCenter(DiagnosticCenter center) {
	// TODO Auto-generated method stub
	return repository.save(center);
	
}
@Override
public DiagnosticCenter getCenterById(String centerId) {
	// TODO Auto-generated method stub
	DiagnosticCenter existingCenter=repository.getOne(centerId);
	if(existingCenter==null)
	{
		
	}
	return existingCenter;
}
@Override
public List<DiagnosticCenter> getAllCenters() {
	// TODO Auto-generated method stub
	List<DiagnosticCenter> centerList=repository.findAll();
	if(centerList==null)
	{
		
	}
	
		return centerList;
	
}



@Override
public boolean removeAllCenters() {
	// TODO Auto-generated method stub
	List<DiagnosticCenter> centerList=repository.findAll();
	if(centerList==null)
	{
		
	}
	return true;
}

@Override
public boolean removeCenter(String centerId) {
	// TODO Auto-generated method stub

	return false;
}
}
