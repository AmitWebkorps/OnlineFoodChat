package com.onlinefoodchat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "client")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	private String name;
	@Column(unique = true)
	private String email;
	private Long phone;
	private String password;
	
	@Column(nullable = true)
	private String restro;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Plan plan;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password="
				+ password + ", restro=" + restro + ", plan=" + plan + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Plan getPlanentity() {
		return plan;
	}

	public void setPlanentity(Plan plan) {
		this.plan = plan;
	}

	public String getRestro() {
		return restro;
	}

	public void setRestro(String restro) {
		this.restro = restro;
	}
}
