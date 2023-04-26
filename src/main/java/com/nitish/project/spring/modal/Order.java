package com.nitish.project.spring.modal;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("order")
	private List<OrderItem> orderItems;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties("orders")
	@JsonIgnore
	private User user;

	public Order() {
		super();
	}

	public Order(long id, User user) {
		super();
		this.id = id;
		this.user = user;
		this.orderItems = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", orderItems=" + orderItems + "]";
	}
}
