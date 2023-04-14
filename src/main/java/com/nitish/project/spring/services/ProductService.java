package com.nitish.project.spring.services;

import java.util.List;

import com.nitish.project.spring.modal.Product;

public interface ProductService {
	public List<Product> getProducts();
	public Product getProduct(Long productId);
	public Product addProduct(Product product);
	public Product updateProduct(Product product);
	public boolean removeProduct(Long productId);
}
