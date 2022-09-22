package com.onlinefoodchat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.Client;
import com.onlinefoodchat.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan,String>{

	public List<Plan> findByExpiryDateGreaterThanEqual(Date now);
	
	public List<Plan> findByClientAndExpiryDateGreaterThanEqual(Client client,Date now);
}
