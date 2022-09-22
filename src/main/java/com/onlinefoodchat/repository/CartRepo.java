package com.onlinefoodchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.Cart;
import com.onlinefoodchat.entity.User;

public interface CartRepo extends JpaRepository<Cart, Integer> {

	public List<Cart> findByUser(User UserId);
}
