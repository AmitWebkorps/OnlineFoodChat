package com.onlinefoodchat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity,String>{

	public List<PlanEntity> findByExpiryDateLessThanEqual(Date now);
}
