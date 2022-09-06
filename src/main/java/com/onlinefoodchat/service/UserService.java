package com.onlinefoodchat.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodchat.entity.ClientEntity;
import com.onlinefoodchat.entity.PlanEntity;
import com.onlinefoodchat.entity.UserEntity;
import com.onlinefoodchat.repository.ClientRepo;
import com.onlinefoodchat.repository.PlanRepo;
import com.onlinefoodchat.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private ClientRepo clientRepo;

	public UserEntity getSignUp(UserEntity userEntity) {
		userEntity.setId(generateUserId(userEntity.getName(), userEntity.getEmail()));
		return userRepo.save(userEntity);
	}

	public String generateUserId(String name, String email) {
		Random random = new Random();
		String num1 = 1000 + random.nextInt(8999) + "";
		String num2 = 1000 + random.nextInt(8999) + "";
		String username = "UR" + num1 + name + num2 + email.substring(0, email.indexOf("@"));
		System.out.println(username);
		return username;
	}

	public UserEntity getLogin(UserEntity userEntity) {
		return userRepo.findByEmailAndPassword(userEntity.getEmail(), userEntity.getPassword());
	}

	public List<PlanEntity> getRestro(){
		
		Date date = new Date();
		List<PlanEntity> plans=planRepo.findByExpiryDateLessThanEqual(date);
		return plans;
	}

}
