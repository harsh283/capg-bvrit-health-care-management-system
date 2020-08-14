package com.capg.hcms.usermanagementsystem.service;

import java.util.List;

import com.capg.hcms.usermanagementsystem.exceptions.ContactNumberAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.EmailAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.UserEmailInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserPasswordInvalidException;
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
}
