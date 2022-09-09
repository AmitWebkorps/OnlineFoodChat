package com.onlinefoodchat.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinefoodchat.entity.ClientEntity;
import com.onlinefoodchat.entity.PlanEntity;

public class PLanAndClientDto {

	@JsonIgnore
	private List<PlanEntity> planEntity;
	private List<ClientEntity> clientEntity;
	public List<PlanEntity> getPlanEntity() {
		return planEntity;
	}
	public void setPlanEntity(List<PlanEntity> planEntity) {
		this.planEntity = planEntity;
	}
	public List<ClientEntity> getClientEntity() {
		return clientEntity;
	}
	public void setClientEntity(List<ClientEntity> clientEntity) {
		this.clientEntity = clientEntity;
	}
	
	
}
