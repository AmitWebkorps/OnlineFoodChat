package com.onlinefoodchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.Client;
import com.onlinefoodchat.entity.Menu;

public interface MenuRepo extends JpaRepository<Menu, Integer>{

	public List<Menu> findByClient(Client client);
}
