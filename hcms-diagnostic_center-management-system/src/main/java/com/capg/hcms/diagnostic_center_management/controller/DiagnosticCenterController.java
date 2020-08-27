package com.capg.hcms.diagnostic_center_management.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.hcms.diagnostic_center_management.exceptions.CenterAlreadyExistsException;
import com.capg.hcms.diagnostic_center_management.exceptions.CenterNameAlreadyExistsException;
import com.capg.hcms.diagnostic_center_management.exceptions.NoCentersAreAvailableException;
import com.capg.hcms.diagnostic_center_management.exceptions.SpecifiedCenterDoesnotExistException;
import com.capg.hcms.diagnostic_center_management.model.DiagnosticCenter;
import com.capg.hcms.diagnostic_center_management.service.DiagnosticCenterServiceImplementation;


@RestController
@RequestMapping("/center")
public class DiagnosticCenterController {
	@Autowired 
	private DiagnosticCenterServiceImplementation centerService;
	
	@GetMapping("/getcenter/center-Id/{centerId}")
	public DiagnosticCenter getCenterById(@PathVariable String centerId) throws SpecifiedCenterDoesnotExistException
	{
		return centerService.getCenterById(centerId);
	}
	@GetMapping("/getallcenters")
	public List<DiagnosticCenter> getAllCenters() throws NoCentersAreAvailableException
	{
		return centerService.getAllCenters();
	}
	
	@PostMapping("/addcenter")
	public DiagnosticCenter addCenter(@RequestBody DiagnosticCenter center) throws  NoCentersAreAvailableException, CenterNameAlreadyExistsException, CenterAlreadyExistsException
	{
	
		return centerService.addCenter(center);
	}

	@DeleteMapping("/removecenter/centerId/{centerId}")
	public boolean removeCenter(@PathVariable String centerId) throws SpecifiedCenterDoesnotExistException
	{
		return centerService.removeCenter(centerId);
	}
	
	@DeleteMapping("/removeAll")
	public boolean removeAllCenters() throws NoCentersAreAvailableException
	{
		return centerService.removeAllCenters();
	}

	@PutMapping("/addtestid/{centerId}/testId/{testId}")
	public DiagnosticCenter assignTestId(@PathVariable String centerId,@PathVariable String testId) throws  SpecifiedCenterDoesnotExistException
	{
		return centerService.addTestId(centerId, testId);
	}
	@PutMapping("/addappointmentid/{centerId}/appointmentid/{appointmentId}")
	public DiagnosticCenter assignAppointment(@PathVariable String centerId,@PathVariable BigInteger appointmentId)
	{
		return centerService.addAppointmentId(centerId, appointmentId);
	}
	@PutMapping("/remove-testid/{centerId}/test-id/{testId}")
	public boolean removeTestId(@PathVariable String centerId,@PathVariable String testId)
	{
		return centerService.removeTestId(centerId, testId);
	}
	@GetMapping("/removealltests")
	public List<DiagnosticCenter> setTestsToNull()
	{
		return centerService.setTestsToNull();
	}

}
