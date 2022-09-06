package com.onlinefoodchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.ClientEntity;
import com.onlinefoodchat.entity.MenuEntity;

public interface MenuRepo extends JpaRepository<MenuEntity, Integer>{

	public List<MenuEntity> findByClientEntity(ClientEntity clientEntity);
}
