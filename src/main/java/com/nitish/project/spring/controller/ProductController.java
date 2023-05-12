package com.nitish.project.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitish.project.spring.modal.Product;
import com.nitish.project.spring.services.ProductService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	// Product Management

		// get all the products
		@GetMapping
		public List<Product> getProducts() {
			return this.productService.getProducts();
		}

		// add product
		
		// add multiple products
		@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		@PostMapping("/addProducts")
		public List<Product> addProduct(@RequestBody List<Product> products) {
			for (Product product : products) {
				productService.addProduct(product);
			}
			return productService.getProducts();
		}

		// add single product
		@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		@PostMapping("/addProduct")
		public Product addProduct(@RequestBody Product product) {
			return this.productService.addProduct(product);
		}

		// update product
		@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		@PutMapping("/update")
		public Product updateProduct(@RequestBody Product product) {
			return this.productService.updateProduct(product);
		}

		// get single product
		@GetMapping("/{productId}")
		public Product getProduct(@PathVariable String productId) {
			return this.productService.getProduct(Long.parseLong(productId));
		}

		@GetMapping("/category/{category}")
		public List<Product> getProductsByCategory(@PathVariable String category) {
			return this.productService.getProductsByCategory(category);
		}

		// get product by search string
		@GetMapping("search/{searchString}")
		public List<Product> getProductsBySerach(@PathVariable String searchString) {
			return this.productService.getProductsBySearch(searchString);
		}

		// get filtered product by category
		@PostMapping("products/{category}/getFilteredProducts")
		public List<Product> getProductsByCategoryWithFilter(@PathVariable String searchString,
				@RequestBody String[] filterNames) {

			return null;
		}

		// remove product
		@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		@DeleteMapping("/getById/{productId}")
		public ResponseEntity<HttpStatus> removeProduct(@PathVariable String productId) {
			try {
				this.productService.removeProduct(Long.parseLong(productId));
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
}
