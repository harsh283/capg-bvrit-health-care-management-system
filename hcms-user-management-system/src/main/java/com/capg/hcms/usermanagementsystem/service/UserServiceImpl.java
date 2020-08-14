package com.capg.hcms.usermanagementsystem.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hcms.usermanagementsystem.exceptions.ContactNumberAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.EmailAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.UserEmailInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameInvalidException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNotFoundException;
import com.capg.hcms.usermanagementsystem.exceptions.UserPasswordInvalidException;
import com.capg.hcms.usermanagementsystem.model.User;
import com.capg.hcms.usermanagementsystem.repo.UserRepo;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	UserRepo userRepo;

	@Override
	public User registerUser(User user) throws UserNameInvalidException, 
	  UserPasswordInvalidException,UserEmailInvalidException, 
	  ContactNumberAlreadyExistException,UserNameAlreadyExistException,EmailAlreadyExistException 
	     {
		user.setUserRole("user");
		Pattern p1=Pattern.compile("[A-Z]{1}[a-zA-Z0-9]{6,14}$");
		Matcher m1=p1.matcher(user.getUserName());
		Pattern p2=Pattern.compile("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$");
		Matcher m2=p2.matcher(user.getUserPassword());
		Pattern p3=Pattern.compile("[0-9a-zA-Z][0-9a-zA-Z._]*[@]gmail.com");
		Matcher m3=p3.matcher(user.getUserEmail());
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
		else if(userRepo.getUserByUserName(user.getUserName())!=null)
			throw new UserNameAlreadyExistException("User with Name "+user.getUserName()+"already exist");
		
		else if(userRepo.getUserByContactNumber(user.getContactNumber())!=null)
			throw new ContactNumberAlreadyExistException("User with ContactNumber "+user.getContactNumber()+"already exist");
		
		else if(userRepo.getUserByUserEmail(user.getUserEmail())!=null)
			throw new EmailAlreadyExistException("User with Email "+user.getUserEmail()+"already exist");
		else
		     userRepo.save(user);
		return user;
			
	}

	@Override
	public boolean deleteUser(String userId) {
		userRepo.deleteById(userId);
		return true;
	}

	@Override
	public User updateUser(User user) {
		User existingUser=userRepo.getOne(user.getUserId());
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
		
		return userRepo.findAll();
	}

	@Override
	public boolean deleteAllUsers() {
		
		 userRepo.deleteAll();
		 return true;
	}

}
