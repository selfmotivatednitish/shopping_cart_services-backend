package com.nitish.project.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitish.project.spring.dao.ProductDao;
import com.nitish.project.spring.modal.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> getProducts() {
		return productDao.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Product getProduct(Long productId) {
		return productDao.getOne(productId);
	}

	@Override
	public Product addProduct(Product product) {
		return productDao.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productDao.save(product);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean removeProduct(Long productId) {
		Product product = productDao.getOne(productId);
		productDao.delete(product);
		return true;
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productDao.findByCategory(category);
	}

	@Override
	public List<Product> getProductsBySearch(String searchString) {
		return productDao.findByCategoryContaining(searchString);
	}

	@Override
	public List<Product> getProductsByCategoryWithFilter(String category, String[] filters) {
		// TODO Auto-generated method stub
		return null;
	}
}
