package com.nitish.project.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitish.project.spring.modal.CartItem;

import jakarta.transaction.Transactional;

public interface CartItemDao extends JpaRepository<CartItem, Long> {
	public List<CartItem> findAllByUserId(Object userId);
	@Transactional
	void deleteByUserIdAndProductId(Object userId, Object productId);
	CartItem findByUserIdAndProductId(Object userId, Object productId);
}
