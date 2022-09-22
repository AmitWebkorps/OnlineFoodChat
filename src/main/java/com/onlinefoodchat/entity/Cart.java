package com.onlinefoodchat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
   	private int quantity;
   	
   	@OneToOne
   	@JoinColumn(name = "menuId")
   	private Menu menu;

   	@ManyToOne
   	@JoinColumn(name = "userId")
   	private User user;
   	
   	@ManyToOne
	@JoinColumn(name = "clientId")
	private Client client;

   	
   	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", quantity=" + quantity + ", menu=" + menu + ", user=" + user
				+ ", client=" + client + "]";
	}
}
