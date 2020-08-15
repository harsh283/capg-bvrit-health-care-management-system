package com.capg.hcms.usermanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.hcms.usermanagementsystem.model.User;
import com.capg.hcms.usermanagementsystem.repo.UserRepo;
import com.capg.hcms.usermanagementsystem.service.IUserService;

@RestController
public class AdminController {
	@Autowired
	IUserService userService;
	
	
	@RequestMapping("/getallusers")
	public List<User> getAllUsers()
	{
       return userService.getAllUsers();
	}
    @RequestMapping("/deleteallusers")
	public boolean deleteAllUsers()
	{
		 userService.deleteAllUsers();
		 return true;
	}
    @RequestMapping("admin/deleteuser/userId/{userId}")
	public boolean deleteUser(@PathVariable String userId)
	{
		return userService.deleteUser(userId);
	}
    @GetMapping("admin/getuser/userId/{userId}")
	public User getUserById(@PathVariable String userId)
	{
		return userService.getUserById(userId);
	}

}
