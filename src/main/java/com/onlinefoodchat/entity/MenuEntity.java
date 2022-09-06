package com.onlinefoodchat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.Nullable;
@Entity
@Table(name ="menu")
public class MenuEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "Client_id")
	private ClientEntity clientEntity;
	
	private String name;
	private double price;
	
	@Column(nullable = true)
	private String imageName;

	public MenuEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClientEntity getClientEntity() {
		return clientEntity;
	}

	public void setClientEntity(ClientEntity clientEntity) {
		this.clientEntity = clientEntity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "MenuEntity [id=" + id + ", clientEntity=" + clientEntity + ", name=" + name + ", price=" + price
				+ ", imageName=" + imageName + "]";
	}


}
