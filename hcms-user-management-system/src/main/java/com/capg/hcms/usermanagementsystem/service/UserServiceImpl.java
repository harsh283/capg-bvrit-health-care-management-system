package com.capg.hcms.usermanagementsystem.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hcms.usermanagementsystem.model.User;
import com.capg.hcms.usermanagementsystem.repo.UserRepo;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	UserRepo userRepo;

	@Override
	public User registerUser(User user) {
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
			//System.out.println("invalid user name");
		}
		else if(!( m2.find() &&  m2.group().equals(user.getUserPassword())) )
		{
   			//System.out.println("invalid user password");
		}
		else if(!( m3.find() &&  m3.group().equals(user.getUserEmail())) )
		{
   			//System.out.println("invalid user email");
		}
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
		return userRepo.getOne(userId);
	
	}

}
