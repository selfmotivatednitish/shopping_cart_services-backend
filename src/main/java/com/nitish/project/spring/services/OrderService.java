package com.nitish.project.spring.services;

import java.util.List;

import com.nitish.project.spring.modal.Order;

public interface OrderService {
	public List<Order> getOrders(Long userId);
	public Order createOrder(Order order);
}
