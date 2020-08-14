package com.capg.hcms.usermanagementsystem.model;

import java.util.List;

public class UserList {

	private List<User> usersList;

	public UserList() {
		super();
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	public UserList(List<User> userList) {
		super();
		this.usersList = userList;
	}
    
	@Override
	public String toString() {
		return "UsersList [userList=" + usersList + "]";
	}
}
