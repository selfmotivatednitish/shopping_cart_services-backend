package com.nitish.project.spring.modal;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@Lob
	@Column(columnDefinition = "MEDIUMTEXT")
	private String image;
	private String description;
	private double price;
	private String category;
	private String[] subcategory;
	
	@OneToOne(mappedBy = "product")
	@JsonIgnore
	private CartItem cartItem;
	
	@OneToOne(mappedBy = "product")
	@JsonIgnore
	private OrderItem orderItem;
	
	public Product() {
		super();
	}

	public Product(long id) {
		super();
		this.id = id;
	}

	public Product(long id, String name, String image, String description, double price, String category,
			String[] subcategory) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.price = price;
		this.category = category;
		this.subcategory = subcategory;
		this.cartItem = new CartItem();
		this.orderItem = new OrderItem();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String[] getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String[] subcategory) {
		this.subcategory = subcategory;
	}

	public CartItem getCartItem() {
		return cartItem;
	}

	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", image=" + image + ", description=" + description + ", price="
				+ price + ", category=" + category + ", subcategory=" + Arrays.toString(subcategory) + ", cartItem="
				+ cartItem + ", orderItem=" + orderItem + "]";
	}
}
