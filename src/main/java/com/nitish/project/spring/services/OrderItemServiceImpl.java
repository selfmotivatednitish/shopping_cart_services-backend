package com.nitish.project.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitish.project.spring.dao.OrderItemDao;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	
	@Autowired
	private OrderItemDao orderItemDao;

	@Override
	public boolean removeOrderItem(Long orderItemId) {
		try {
			orderItemDao.deleteById(orderItemId);
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}
