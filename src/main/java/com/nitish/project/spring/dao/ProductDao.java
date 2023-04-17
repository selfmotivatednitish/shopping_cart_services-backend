package com.nitish.project.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitish.project.spring.modal.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
	public List<Product> findByCategory(String category);
	public List<Product> findByCategoryContaining(String category);
}
