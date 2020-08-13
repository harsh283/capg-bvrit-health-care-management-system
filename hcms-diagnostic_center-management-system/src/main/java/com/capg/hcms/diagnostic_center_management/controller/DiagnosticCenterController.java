package com.capg.hcms.diagnostic_center_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.hcms.diagnostic_center_management.model.DiagnosticCenter;
import com.capg.hcms.diagnostic_center_management.service.DiagnosticCenterServiceImplementation;


@RestController
public class DiagnosticCenterController {
	@Autowired 
	private DiagnosticCenterServiceImplementation centerService;
	
	@PostMapping("/addcenter")
	public DiagnosticCenter addCenter(DiagnosticCenter center)
	{
		return centerService.addCenter(center);
	}
	@GetMapping("/getcenter/centerId/{centerId}")
	public DiagnosticCenter getCenterById(@PathVariable String centerId)
	{
		return centerService.getCenterById(centerId);
	}
	@GetMapping("/getallcenters")
	public List<DiagnosticCenter> getAllCenters()
	{
		return centerService.getAllCenters();
	}
	@DeleteMapping("/removecenter/centerId/{centerId}")
	public boolean removeCenter(@PathVariable String centerId)
	{
		return centerService.removeCenter(centerId);
	}
	



}
