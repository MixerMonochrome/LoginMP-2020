package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.User;

public interface UserIDAO extends JpaRepository<User,Integer>{
	public User findByUsername(String u);
}
