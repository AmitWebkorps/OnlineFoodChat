package com.onlinefoodchat.service;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		java.util.Date date =new java.util.Date();
		List<PlanEntity> plans=planRepo.findByExpiryDateGreaterThanEqual(new Date(date.getYear(),date.getMonth(),date.getDate()));
		System.out.println(plans);
		return plans;
	}

	public List<PlanEntity> getRestro(String restro) {
		List<PlanEntity> list=getRestro().stream().filter(x->x.getClientEntity().getName().contains(restro)).collect(Collectors.toList());
		System.out.println(list);
	    return list;
	}

}
