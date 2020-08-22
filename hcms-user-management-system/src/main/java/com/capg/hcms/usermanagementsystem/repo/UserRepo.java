package com.capg.hcms.usermanagementsystem.repo;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.hcms.usermanagementsystem.model.User;

public interface UserRepo extends JpaRepository<User,String>{
	
public User getUserByUserName(String userName);
	
	public User getUserByContactNumber(BigInteger contactNumber);
	
	public User getUserByUserEmail(String userEmail);
	
    public User getUserByUserNameAndUserPassword(String userName,String userPassword);

}
