package com.nitish.project.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitish.project.spring.modal.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
	
}
