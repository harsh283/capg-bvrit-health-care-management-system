package com.capg.hcms.usermanagementsystem.service;

import com.capg.hcms.usermanagementsystem.model.User;

public interface IUserService {

	public User registerUser(User user);
	public boolean deleteUser(String userId);
	public User updateUser(User user);
	public User getUserById(String userId);
}
