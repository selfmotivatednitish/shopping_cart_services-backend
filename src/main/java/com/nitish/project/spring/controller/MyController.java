package com.nitish.project.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nitish.project.spring.modal.Product;
import com.nitish.project.spring.services.ProductService;

@RestController
public class MyController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/home")
	public String home() {
		return "this is home page";
	}
	
	// get all the products
	@GetMapping("/products")
	public List<Product>  getProducts() {
		return this.productService.getProducts();
	}
	
	// get single product
	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable String productId) {
		return this.productService.getProduct(Long.parseLong(productId));
	}
	
	// add product
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return this.productService.addProduct(product);
	}
	
	// update product
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product) {
		return this.productService.updateProduct(product);
	}
	
	// remove product
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<HttpStatus> removeProduct(@RequestBody String productId) {
		try {
			this.productService.removeProduct(Long.parseLong(productId));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
