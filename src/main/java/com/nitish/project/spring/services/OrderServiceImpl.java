package com.nitish.project.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitish.project.spring.dao.OrderDao;
import com.nitish.project.spring.modal.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public List<Order> getOrders(Long userId) {
		return orderDao.findByUserId(userId);
	}

	@Override
	public Order createOrder(Order order) {
		return orderDao.save(order);
	}

	@Override
	public List<Order> deleteOrderById(long userId, long orderId) {
		orderDao.deleteById(orderId);		
		return getOrders(userId);
	}

}
