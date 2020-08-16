package com.capg.hcms.usermanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.hcms.usermanagementsystem.exceptions.ContactNumberAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.EmailAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.UserEmailInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserPasswordInvalidException;
import com.capg.hcms.usermanagementsystem.model.Appointment;
import com.capg.hcms.usermanagementsystem.model.User;
import com.capg.hcms.usermanagementsystem.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
     @Autowired
     IUserService userService;
     
     @PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws UserNameInvalidException, 
	  UserPasswordInvalidException,UserEmailInvalidException, 
	  ContactNumberAlreadyExistException,UserNameAlreadyExistException,EmailAlreadyExistException
	{
		return userService.registerUser(user);
	}
	@RequestMapping("/deleteuser/userId/{userId}")
	public boolean deleteUser(@PathVariable String userId)
	{
		return userService.deleteUser(userId);
	}
	@PutMapping("/updateuser")
	public User updateUser(@RequestBody User user)
	{
		return userService.updateUser(user);
	}
	@GetMapping("/getuser/userId/{userId}")
	public User getUserById(@PathVariable String userId)
	{
		return userService.getUserById(userId);
	}
	@PostMapping("/makeappointment/centerid/{centerId}")
	public Appointment makeAppointment(@PathVariable String centerId,@RequestBody Appointment appointment) {
		return userService.makeAppointment(centerId,appointment);
	}
	@GetMapping("/getallappointments")
	public List<Appointment> getAllAppointments()
	{
		return userService.getAllAppointments();
	}
}
