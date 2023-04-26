package com.nitish.project.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitish.project.spring.modal.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Long> {

}
