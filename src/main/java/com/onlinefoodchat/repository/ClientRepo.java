package com.onlinefoodchat.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onlinefoodchat.entity.ClientEntity;
import com.onlinefoodchat.entity.PlanEntity;

public interface ClientRepo extends JpaRepository<ClientEntity, Integer> {

	public ClientEntity findByEmailAndPassword(String email, String password);
    
	public List<ClientEntity> findByRestroContains(String restro);
	
	public List<ClientEntity> findByRestroAndPlanEntity(String restro,PlanEntity planEntity);

	public List<ClientEntity> findByPlanEntity(PlanEntity planEntity);
}
