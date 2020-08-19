package com.capg.hcms.usermanagementsystem.service;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.capg.hcms.usermanagementsystem.exceptions.ContactNumberAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.EmailAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.UserEmailInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNotFoundException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNumberInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserPasswordInvalidException;
import com.capg.hcms.usermanagementsystem.model.Appointment;
import com.capg.hcms.usermanagementsystem.model.DiagnosticCenter;
import com.capg.hcms.usermanagementsystem.model.TestManagement;
import com.capg.hcms.usermanagementsystem.model.User;
import com.capg.hcms.usermanagementsystem.repo.UserRepo;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	UserRepo userRepo;
	@Autowired
	RestTemplate restTemplate;
	@Override
	public User registerUser(User user) throws UserNameInvalidException, 
	  UserPasswordInvalidException,UserEmailInvalidException, UserNumberInvalidException
	  ,UserNameAlreadyExistException,EmailAlreadyExistException 
	     {
		
		Pattern p1=Pattern.compile("[A-Z]{1}[a-zA-Z0-9]{6,14}$");
		Matcher m1=p1.matcher(user.getUserName());
		Pattern p2=Pattern.compile("^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,20}$");
		Matcher m2=p2.matcher(user.getUserPassword());
		Pattern p3=Pattern.compile("^(.+)@(.+)$");
		Matcher m3=p3.matcher(user.getUserEmail());
		Pattern p4=Pattern.compile("\\d{10}");
		Matcher m4=p4.matcher(user.getContactNumber().toString());
		if(!(m1.find() &&  m1.group().equals(user.getUserName())))
		{
			throw new UserNameInvalidException("Username should start with capital letter ad size should be 6-14  characters");
			
		}
		else if(!( m2.find() &&  m2.group().equals(user.getUserPassword())) )
		{
   			throw new UserPasswordInvalidException("User password must contain "
   					+ "capital letter,small letters and special character "
   					+ "without starting with number and range should be between 8 and 20");
		}
		else if(!( m3.find() &&  m3.group().equals(user.getUserEmail())) )
		{
   			throw new UserEmailInvalidException("user email is not valid");
		}
		else if(!( m4.find() &&  m4.group().equals(user.getContactNumber().toString())) )
		{
			throw new UserNumberInvalidException("contact number should contain 10 digits and starting may be 7,8 or 9");
		}
		else if(userRepo.getUserByUserName(user.getUserName())!=null)
			throw new UserNameAlreadyExistException("User with Name "+user.getUserName()+" already exist");
		
		else if(userRepo.getUserByContactNumber(user.getContactNumber())!=null)
			throw new ContactNumberAlreadyExistException("User with ContactNumber "+user.getContactNumber()+" already exist");
		
		else if(userRepo.getUserByUserEmail(user.getUserEmail())!=null)
			throw new EmailAlreadyExistException("User with Email "+user.getUserEmail()+" already exist");
		 else
		     userRepo.save(user);
		return user;
			
	}

	@Override
	public boolean deleteUser(String userId) {
		User user = userRepo.getOne(userId);
		if(user==null)
		{
			throw new UserNotFoundException("User Doesnot exist");
		}
		userRepo.deleteById(userId);
		return true;
	}

	@Override
	public User updateUser(User user) {
		User existingUser=userRepo.getOne(user.getUserId());
		if(existingUser==null)
		{
			throw new UserNotFoundException("User Doesnot exist");
		}
		existingUser.setUserName(user.getUserName());
		existingUser.setUserPassword(user.getUserPassword());
		existingUser.setUserRole(user.getUserRole());
		existingUser.setUserEmail(user.getUserEmail());
		existingUser.setAge(user.getAge());
		existingUser.setContactNumber(user.getContactNumber());
		existingUser.setGender(user.getGender());
		return userRepo.save(existingUser);
	}

	@Override
	public User getUserById(String userId) {
		if(!userRepo.existsById(userId))
			throw new UserNotFoundException("User with id "+userId+" Not Found");
		return userRepo.getOne(userId);
	
	}

	@Override
	public List<User> getAllUsers() {
		if(userRepo.findAll().isEmpty())
		{
			throw new UserNotFoundException("Users unaivailable");
		}
		return userRepo.findAll();
	}

	@Override
	public boolean deleteAllUsers() {
		if(userRepo.findAll().isEmpty())
		{
			throw new UserNotFoundException("Users unaivailable");
		}
		 userRepo.deleteAll();
		 return true;
	}

	

	@Override
	public DiagnosticCenter addCenter(DiagnosticCenter center) {
		// TODO Auto-generated method stub
		ResponseEntity<List<TestManagement>> testManage=restTemplate.exchange("http://hcms-diagnostic-test-management-system/test/add-default", HttpMethod.GET,null,new ParameterizedTypeReference<List<TestManagement>>() {
		});
		List<TestManagement> listTest=testManage.getBody();
		/* System.out.println(listtest); */
		List<String> lists=new ArrayList();
		lists.add(listTest.get(0).getTestId());
		lists.add(listTest.get(1).getTestId());
		String neededId=center.getCenterId();
		center.setTests(lists);
		
		
		DiagnosticCenter centerPosted=restTemplate.postForObject("http://hcms-diagnostic-center-management-system/center/addcenter", center, DiagnosticCenter.class);
		
		
		return centerPosted;
		
		
		
				/*
				 * restTemplate.put(new URI(
				 * "http://localhost:8090/addtestid/"+neededId+"/testId/"+listtest.get(1).
				 * getTestId()), null);
				 */
		//List<TestManagement> testList=restTemplate.getForObject(url, responseType)
	}

	@Override
	public List<DiagnosticCenter> getAllCenters() {
		// TODO Auto-generated method stub
		ResponseEntity<List<DiagnosticCenter>> centerEntity=restTemplate.exchange("http://hcms-diagnostic-center-management-system/center/getallcenters", HttpMethod.GET,null,new ParameterizedTypeReference<List<DiagnosticCenter>>() {
		});
		List<DiagnosticCenter> centerList=centerEntity.getBody();
		return centerList;
	}

	@Override
	public boolean deleteAllCenters() {
		// TODO Auto-generated method stub
		restTemplate.delete("http://hcms-diagnostic-center-management-system/center/removeAll");
		return true;
	}

	@Override
	public DiagnosticCenter getCenterById(String centerId) {
		// TODO Auto-generated method stub
		DiagnosticCenter center=restTemplate.getForObject("http://hcms-diagnostic-center-management-system/center/getcenter/center-Id/"+centerId,DiagnosticCenter.class);
		return center;
	}

	@Override
	public boolean deleteCenterById(String centerId) {
		// TODO Auto-generated method stub
		restTemplate.delete("http://hcms-diagnostic-center-management-system/center/removecenter/centerId/"+centerId);
		return true;
	}

	@Override
	public List<TestManagement> getAllTests() {
		// TODO Auto-generated method stub
		ResponseEntity<List<TestManagement>> testEntity=restTemplate.exchange("http://hcms-diagnostic-test-management-system/test/getAll", HttpMethod.GET,null,new ParameterizedTypeReference<List<TestManagement>>() {
		});
		List<TestManagement> testList=testEntity.getBody();
		return testList;
	}

	@Override
	public TestManagement addTest(String centerId,TestManagement newTest)  {
		// TODO Auto-generated method stub
		//System.out.println("gfdgfh");
		DiagnosticCenter center=restTemplate.getForObject("http://hcms-diagnostic-center-management-system/center/getcenter/center-Id/"+centerId,DiagnosticCenter.class);
		//System.out.println(center);
		
		if(center.getTests()==null)
		{
			List<String> testList=new ArrayList<>();
			testList.add(newTest.getTestId());	
		center.setTests(testList);
		}
		else
		{
			center.getTests().add(newTest.getTestId());
		}
		restTemplate.put(("http://hcms-diagnostic-center-management-system/center/addtestid/"+centerId+"/testId/"+newTest.getTestId()), DiagnosticCenter.class);
		TestManagement  addedTest=restTemplate.postForObject("http://hcms-diagnostic-test-management-system/test/addTest",newTest,TestManagement.class);
		return  addedTest;
	}

	@Override
	public boolean deleteTestById(String centerId, String testId) {
		// TODO Auto-generated method stub
		DiagnosticCenter center= restTemplate.getForObject("http://hcms-diagnostic-center-management-system/center/getcenter/center-Id/"+centerId,DiagnosticCenter.class);
		restTemplate.delete("http://hcms-diagnostic-test-management-system/test/deleteTest/id/"+testId);
		restTemplate.put(("http://hcms-diagnostic-center-management-system/center/remove-testid/"+centerId+"/test-id/"+testId), null);
		return true;
	}

	@Override
	public TestManagement getTestById(String testId) {
		// TODO Auto-generated method stub
		TestManagement existingTest=restTemplate.getForObject("http://hcms-diagnostic-test-management-system/test/getTest/id/"+testId, TestManagement.class);
		return existingTest;
	}

	@Override
	public boolean deleteAllTests() {
		// TODO Auto-generated method stub
		
		restTemplate.delete("http://hcms-diagnostic-test-management-system/test/deleteAll");
	//	restTemplate.delete("http://localhost:8090/removealltests");
		ResponseEntity<List<DiagnosticCenter>> centerEntity=restTemplate.exchange("http://hcms-diagnostic-center-management-system/center/removealltests", HttpMethod.GET,null,new ParameterizedTypeReference<List<DiagnosticCenter>>() {});
		List<DiagnosticCenter> centerList=centerEntity.getBody();
		return true;
	}

	@Override
	public Appointment makeAppointment(String centerId,Appointment appointment) {
		
		Appointment newappointment = restTemplate.postForObject("http://hcms-appointment-management-system/appointmentuser/makeappointment",appointment, Appointment.class);

		restTemplate.put(("http://hcms-diagnostic-center-management-system/center/addappointmentid/"+centerId+"/appointmentid/"+ newappointment.getAppointmentId()), DiagnosticCenter.class);

		return newappointment;
		
	}

	@Override
	public List<Appointment> getAllAppointments() {
		// TODO Auto-generated method stub
		ResponseEntity<List<Appointment>> appointmentEntity=restTemplate.exchange("http://hcms-appointment-management-system/appointmentuser/getallappointments", HttpMethod.GET,null,new ParameterizedTypeReference<List<Appointment>>() {
		});
		List<Appointment> appointmentList=appointmentEntity.getBody();
		return appointmentList;
	}

	@Override
	public Appointment approveAppointment(BigInteger appointmentId, boolean status) {
		// TODO Auto-generated method stub
		List<Appointment> appointmentList=getAllAppointments();
		//System.out.println("dasghfkjlg;;sdjkfglgsdfgl;kjasdkflgl;df;lkj"+appointmentList);
		restTemplate.put("http://hcms-appointment-management-system/appointmentadmin/approveAppointment/" + appointmentId + "/status/" + status, null);
		Appointment approvee= restTemplate.getForObject("http://hcms-appointment-management-system/appointmentadmin/getAppointment/" + appointmentId,
				Appointment.class);
//List<Appointment> appointmentList=getAllAppointments();
//System.out.println(appointmentList);
return approvee;
	
		
	}
	
}
