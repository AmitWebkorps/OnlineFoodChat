package com.onlinefoodchat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonRawValue;

@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String ClientNotification;

	@OneToOne
	private Client client;

	@OneToOne
	private User user;
	
	@OneToOne
	private Order order;

	private String userNotification;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientNotification() {
		return ClientNotification;
	}

	public void setClientNotification(String clientNotification) {
		ClientNotification = clientNotification;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserNotification() {
		return userNotification;
	}

	public void setUserNotification(String userNotification) {
		this.userNotification = userNotification;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}
