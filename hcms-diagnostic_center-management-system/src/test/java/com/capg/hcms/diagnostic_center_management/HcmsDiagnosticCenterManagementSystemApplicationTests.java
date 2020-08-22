package com.capg.hcms.diagnostic_center_management;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.hcms.diagnostic_center_management.exceptions.CenterNameAlreadyExistsException;
import com.capg.hcms.diagnostic_center_management.exceptions.NoCentersAreAvailableException;
import com.capg.hcms.diagnostic_center_management.model.DiagnosticCenter;
import com.capg.hcms.diagnostic_center_management.repo.DiagnosticCenterRepo;
import com.capg.hcms.diagnostic_center_management.service.DiagnosticCenterServiceImplementation;

@SpringBootTest
class HcmsDiagnosticCenterManagementSystemApplicationTests {

	@Autowired
	DiagnosticCenterRepo repo;
	
	@Autowired
	DiagnosticCenterServiceImplementation service;
	DiagnosticCenter center1;
	DiagnosticCenter center2;
	DiagnosticCenter center3;
	
	@BeforeEach
	public void init()
	{
		List<String> tests=new ArrayList<String>();
		List<BigInteger> appointments=new ArrayList<BigInteger>();		
		center1=new DiagnosticCenter("","Nims Hostipals",tests,appointments);
		center2=new DiagnosticCenter("76348","Rohan's CLinic",null,null);
		center3=new DiagnosticCenter("71546","Elisa Diagnostic Center",null,null);
	}
	
	
	
	@Test
	public void testAddCenterException() {
		 
		 Assertions.assertThrows(CenterNameAlreadyExistsException.class, () -> {
		      service.addCenter(center3);
		    });
	} 
	
	
/* 	@Test
 	public void testRemoveCenter()
 	{		
 		service.removeCenter(center2);
 		assertEquals(false,repo.getByCenterName("Rohan's CLinic")!=null);
 	}
*/
	
	@Test
	public void testRemoveCenterException() {
		 Assertions.assertThrows(NoCentersAreAvailableException.class, () -> {
		      service.removeCenter(center2.getCenterId());
		    });
	}

}
