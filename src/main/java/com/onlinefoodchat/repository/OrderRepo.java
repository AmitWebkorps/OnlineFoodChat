package com.onlinefoodchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.Client;
import com.onlinefoodchat.entity.Order;
import com.onlinefoodchat.entity.User;

public interface OrderRepo extends JpaRepository<Order, Integer> {

	public List<Order> findByUser(User user);
	public List<Order> findByClient(Client client);
}
