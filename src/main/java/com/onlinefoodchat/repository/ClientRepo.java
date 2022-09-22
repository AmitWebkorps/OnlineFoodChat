package com.onlinefoodchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.Client;
import com.onlinefoodchat.entity.Plan;

public interface ClientRepo extends JpaRepository<Client, Integer> {

	public Client findByEmailAndPassword(String email, String password);
    
	public List<Client> findByRestroContains(String restro);
	
	public List<Client> findByRestroAndPlan(String restro,Plan plan);

	public List<Client> findByPlan(Plan plan);
}
