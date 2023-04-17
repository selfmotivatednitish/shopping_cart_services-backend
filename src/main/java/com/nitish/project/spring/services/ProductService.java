package com.nitish.project.spring.services;

import java.util.List;

import com.nitish.project.spring.modal.Product;

public interface ProductService {
	public List<Product> getProducts();

	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public Product getProduct(Long productId);

	public List<Product> getProductsByCategory(String category);

	public List<Product> getProductsBySearch(String searchString);

	public List<Product> getProductsByCategoryWithFilter(String category, String[] filters);

	public boolean removeProduct(Long productId);
}
