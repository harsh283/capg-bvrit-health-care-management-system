/**
	* Project Name : Health Care Management System
	*
	* 
**/
package com.capg.hcms.diagnostic_center_management.service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hcms.diagnostic_center_management.exceptions.CenterAlreadyExistsException;
import com.capg.hcms.diagnostic_center_management.exceptions.CenterNameAlreadyExistsException;
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
	
			/**
			* Fetches a center by taking specified centerId as input parameter
			* @return   :DiagnosticCenter Object 
			* @throws   :SpecifiedCenterDoesnotExist exception
			* @author   :Shambu Harsh Kumar
			* @since    :2020-08-17 
			**/
		@Override
		public DiagnosticCenter getCenterById(String centerId) throws SpecifiedCenterDoesnotExistException {
			boolean existingCenter = repository.existsById(centerId);
			if(existingCenter)
			{
				DiagnosticCenter center = repository.getOne(centerId);
				return center;
			}
			throw new SpecifiedCenterDoesnotExistException("Center with center id "+centerId+"Doesnot exist");
			
		}
			/**
			* Fetches all centers in the repository
			* @return   :List of DiagnosticCenters  
			* @throws   :NoCentersAreAvailableException
			* @author   :Shambu Harsh Kumar
			* @since    :2020-08-17 
			**/
		@Override
		public List<DiagnosticCenter> getAllCenters() throws NoCentersAreAvailableException {
			
			List<DiagnosticCenter> centerList=repository.findAll();
			
			if(centerList.isEmpty())
			{
				throw new NoCentersAreAvailableException("No center is present");	
			}
			
				return centerList;	
		}

		/**
		* Adds a Diagnostic Center in the repository
		* @return   :DiagnosticCenter Object
		* @throws   :NoCentersAreAvailableException
		* @author   :Shambu Harsh Kumar
		* @since    :2020-08-17 
		**/
		@Override
		public DiagnosticCenter addCenter(DiagnosticCenter center) throws NoCentersAreAvailableException, CenterNameAlreadyExistsException, CenterAlreadyExistsException {
			center.setCenterId(String.valueOf(random.nextInt(10000000)));
			if(repository.existsById(center.getCenterId()))
			{
				throw new CenterAlreadyExistsException("Center already exists kindly enter another center ID");
			}
			
			if(repository.existsByCenterName(center.getCenterName()))
			{
				throw new CenterNameAlreadyExistsException("Center Name Already exists Kindly enter some other name ");
			}
			
			return repository.save(center);
			
		}

		/**
		* Deletes all  Diagnostic Centers in the repository
		* @return   :Boolean
		* @throws   :NoCentersAreAvailableException
		* @author   :Shambu Harsh Kumar
		* @since    :2020-08-17 
		**/
		@Override
		public boolean removeAllCenters() throws NoCentersAreAvailableException {
			List<DiagnosticCenter> centerList=repository.findAll();
			if(centerList.isEmpty())
			{
				
				throw new NoCentersAreAvailableException("Centers are not present");
			}
			repository.deleteAll();
			return true;
		}
		/**
		* Deletes a specified center in the DiagnosticCenter repository
		* @return   :Boolean
		* @throws   :NoCentersAreAvailableException
		* @author   :Shambu Harsh Kumar
		 * @throws SpecifiedCenterDoesnotExistException 
		* @since    :2020-08-17 
		**/
		@Override
		public boolean removeCenter(String centerId) throws  SpecifiedCenterDoesnotExistException {
			
			if(repository.existsById(centerId))
			{
				repository.deleteById(centerId);
				return true;
			}
		
			else {
				throw new SpecifiedCenterDoesnotExistException("Center doesnot exist kindly enter another center ID");
			}
		}
		
		/**
		* Adds testId in the diagnosticCenter repository by taking centerId and adding testId in Center Object
		* @return   :DiagnosticCenter Object
		* @throws   :SpecifiedCenterDoesnotExistException
		* @author   :Shambu Harsh Kumar
		 * @throws SpecifiedCenterDoesnotExistException 
		* @since    :2020-08-17 
		**/
		@Override
		public DiagnosticCenter addTestId(String centerId, String testId) throws SpecifiedCenterDoesnotExistException {
			
			DiagnosticCenter existingCenter=repository.getOne(centerId);
			if(existingCenter==null)
			{
				throw new SpecifiedCenterDoesnotExistException("Center with specified centerId unavailable");
			}
			existingCenter.getTests().add(testId);
			return repository.save(existingCenter);
		}
		
		/**
		* Adds appointmentId in the diagnosticCenter repository by taking centerId and adding appointmentId in Center Object
		* @return   :DiagnosticCenter object
		* @author   :Shambu Harsh Kumar
		* @since    :2020-08-17 
		**/
		@Override
		public DiagnosticCenter addAppointmentId(String centerId, BigInteger appointmentId) {
			
			DiagnosticCenter existingCenter=repository.getOne(centerId);
			List<BigInteger> appointmentList=new ArrayList<>();
			if(existingCenter.getAppointments()==null)
			{
				appointmentList.add(appointmentId);
			existingCenter.setAppointments(appointmentList);
			}
			else
			{
				existingCenter.getAppointments().add(appointmentId);
			}
			
			return repository.save(existingCenter);
		}
		
		/**
		* Removes testId in the diagnosticCenter repository by taking centerId and adding testId in Center Object
		* @return   :DiagnosticCenter Object
		* @author   :Shambu Harsh Kumar
		 * @throws SpecifiedCenterDoesnotExistException 
		* @since    :2020-08-17 
		**/
		
		@Override
		public boolean removeTestId(String centerId, String testId) {
			
			DiagnosticCenter center=repository.getOne(centerId);
			
			center.getTests().remove(testId);
			
			repository.save(center);
			
			return true;
		
		}
		
		/**
		* Sets the list of tests to null when the tests are removed in test table
		* @return   :DiagnosticCenter Object
		* @throws   :SpecifiedCenterDoesnotExistException
		* @author   :Shambu Harsh Kumar
		* @since    :2020-08-17 
		**/
		@Override
		public List<DiagnosticCenter> setTestsToNull() {
		
			List<DiagnosticCenter> centerList=repository.findAll();
			for (DiagnosticCenter diagnosticCenter : centerList) {
				diagnosticCenter.setTests(null);
				repository.save(diagnosticCenter);
			}
			List<DiagnosticCenter> nullTestList=repository.findAll();
			
			return nullTestList;
		}

}
