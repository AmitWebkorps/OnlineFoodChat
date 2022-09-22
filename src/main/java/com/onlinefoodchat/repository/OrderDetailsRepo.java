package com.onlinefoodchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {

}
