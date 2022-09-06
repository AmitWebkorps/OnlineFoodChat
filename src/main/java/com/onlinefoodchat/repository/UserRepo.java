package com.onlinefoodchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity,String>{
	
	public UserEntity findByEmailAndPassword(String email,String password);
}
