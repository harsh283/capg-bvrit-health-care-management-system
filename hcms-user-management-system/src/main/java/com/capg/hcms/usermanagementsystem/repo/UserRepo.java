package com.capg.hcms.usermanagementsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.hcms.usermanagementsystem.model.User;

public interface UserRepo extends JpaRepository<User,String>{

}
