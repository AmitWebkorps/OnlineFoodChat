package com.onlinefoodchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.User;

public interface UserRepo extends JpaRepository<User,String>{
	
	public User findByEmailAndPassword(String email,String password);
}
