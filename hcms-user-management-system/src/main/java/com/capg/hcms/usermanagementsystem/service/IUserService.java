package com.capg.hcms.usermanagementsystem.service;

import java.math.BigInteger;
import java.util.List;

import com.capg.hcms.usermanagementsystem.exceptions.ContactNumberAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.EmailAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.UserEmailInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserPasswordInvalidException;
import com.capg.hcms.usermanagementsystem.model.Appointment;
import com.capg.hcms.usermanagementsystem.model.DiagnosticCenter;
import com.capg.hcms.usermanagementsystem.model.TestManagement;
import com.capg.hcms.usermanagementsystem.model.User;

public interface IUserService {

	public User registerUser(User user) throws UserNameInvalidException, 
	  UserPasswordInvalidException,UserEmailInvalidException, 
	  ContactNumberAlreadyExistException,UserNameAlreadyExistException,EmailAlreadyExistException;
	public boolean deleteUser(String userId);
	public User updateUser(User user);
	public User getUserById(String userId);
	public List<User> getAllUsers();
	public boolean deleteAllUsers();

	public DiagnosticCenter addCenter(DiagnosticCenter center);
	public List<DiagnosticCenter> getAllCenters();
	public boolean deleteAllCenters();
	public DiagnosticCenter getCenterById(String centerId);
	public boolean deleteCenterById(String centerId);
	
	public List<TestManagement> getAllTests();
	
	public TestManagement addTest(String centerId, TestManagement newTest) ;
	public  boolean deleteTestById(String centerId, String testId);
	
	//public TestManagement getTestById(String testId);
	public TestManagement getTestById(String testId);
	public boolean deleteAllTests();
	public Appointment makeAppointment(String centerId,Appointment appointment);
	public List<Appointment> getAllAppointments();
	
	Appointment approveAppointment(BigInteger appointmentId, boolean status);
}
