package com.capg.hcms.usermanagementsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.hcms.usermanagementsystem.exceptions.ContactNumberAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.EmailAlreadyExistException;
import com.capg.hcms.usermanagementsystem.exceptions.UserNameAlreadyExistException;
import com.capg.hcms.usermanagementsystem.model.User;
import com.capg.hcms.usermanagementsystem.service.IUserService;

@SpringBootTest
class HcmsUserManagementSystemApplicationTests {

	@Autowired
	IUserService userService;

	User user1;
	User user2;
	User user3;
	
	@BeforeEach
	public void init()
	{	
	   user1=new User("","Dhanush","Ravalibon@12",new BigInteger("9010330067"),"rava@gmail.com","user",21,"female","");   
	   user2=new User("","Praneetha","Dhanush@1",new BigInteger("9848969667"),"pranu@gmail.com","user",21,"female","");
	   user3=new User("","Pragnapadma","Pragnapadma@1",new BigInteger("9908596905"),"sindhu@gmail.com","user",21,"female","");
	}

	@Test
	void testUserNameException()
	{
	    Assertions.assertThrows(UserNameAlreadyExistException.class, ()->
	    {
	    	userService.registerUser(user1);
	    });   		  
	}
	
	@Test
	void testContactNumberException()
	{
	    Assertions.assertThrows(ContactNumberAlreadyExistException.class, ()->
	    {
	    	userService.registerUser(user2);
	    });		  
	}
	
	@Test
	void testEmailException()
	{
	    Assertions.assertThrows(EmailAlreadyExistException.class, ()->
	    {
	    	userService.registerUser(user3);
	    });
	   		  
	}

}
