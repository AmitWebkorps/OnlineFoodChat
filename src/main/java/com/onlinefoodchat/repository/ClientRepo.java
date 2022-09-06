package com.onlinefoodchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.ClientEntity;
import com.onlinefoodchat.entity.PlanEntity;

public interface ClientRepo extends JpaRepository<ClientEntity, Integer>{

	public ClientEntity findByEmailAndPassword(String email,String password);
	
	public List<ClientEntity> findByPlanEntity( PlanEntity planEntity);
}
