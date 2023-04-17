package com.nitish.project.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitish.project.spring.modal.Order;

public interface OrderDao extends JpaRepository<Order, Long> {
	public List<Order> findByUserId(Object userId);
}
