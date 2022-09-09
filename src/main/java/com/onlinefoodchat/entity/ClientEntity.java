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
public class ClientEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	public PlanEntity getPlanEntity() {
		return planEntity;
	}

	public void setPlanEntity(PlanEntity planEntity) {
		this.planEntity = planEntity;
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
	private PlanEntity planEntity;


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
		return "ClientEntity [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password="
				+ password + ", restro=" + restro + ", planEntity=" + planEntity + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PlanEntity getPlanentity() {
		return planEntity;
	}

	public void setPlanentity(PlanEntity planEntity) {
		this.planEntity = planEntity;
	}

	public String getRestro() {
		return restro;
	}

	public void setRestro(String restro) {
		this.restro = restro;
	}
}
