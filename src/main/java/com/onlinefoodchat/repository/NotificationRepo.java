package com.onlinefoodchat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.onlinefoodchat.entity.Client;
import com.onlinefoodchat.entity.Notification;
import com.onlinefoodchat.entity.Order;
import com.onlinefoodchat.entity.User;

public interface NotificationRepo extends CrudRepository<Notification, Integer>{

	public List<Notification> findByUser(User user);
	public List<Notification> findByClient(Client client);
	public Notification findByOrder(Order order);
}
