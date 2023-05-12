package com.nitish.project.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitish.project.spring.modal.CartItem;
import com.nitish.project.spring.modal.Order;
import com.nitish.project.spring.modal.OrderItem;
import com.nitish.project.spring.modal.User;
import com.nitish.project.spring.services.CartItemService;
import com.nitish.project.spring.services.OrderItemService;
import com.nitish.project.spring.services.OrderService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@GetMapping("/{userId}/getOrders")
	public List<Order> getOrders(@PathVariable String userId) {
		return orderService.getOrders(Long.parseLong(userId));
	}
	
	

	@PostMapping("/{userId}/createOrder")
	public Order createOrder(@RequestBody CartItem cartItem, @PathVariable String userId) {
		Order order = new Order();
		order.setUser(new User(Long.parseLong(userId)));
		List<OrderItem> orderItems = new ArrayList<>();

		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);
		orderItem.setProduct(cartItem.getProduct());
		orderItem.setQuantity(cartItem.getQuantity());
		orderItem.setOrderStatus("Order Created");
		orderItems.add(orderItem);

		order.setOrderItems(orderItems);
		return orderService.createOrder(order);
	}
	
	@PostMapping("/{userId}/checkout")
	public ResponseEntity<List<Order>> checkoutCart(@PathVariable String userId) {
		List<CartItem> cartItems = cartItemService.getAllCartItems(Long.parseLong(userId));
		Order order = new Order();
		order.setUser((new User(Long.parseLong(userId))));
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem cartItem : cartItems) {
			cartItemService.removeCartItem(Long.parseLong(userId), cartItem.getProduct().getId());
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setOrderStatus("Order Created");
			orderItems.add(orderItem);
		}
		
		order.setOrderItems(orderItems);
		orderService.createOrder(order);
		
		List<Order> orders = orderService.getOrders(Long.parseLong(userId));
		
		return ResponseEntity.ok(orders);
	}
	
	@DeleteMapping("/{userId}/cancelOrderItem/{OrderItemId}")
	public ResponseEntity<List<Order>> deleteOrderItem(@PathVariable String userId, @PathVariable String OrderItemId) {
		if(orderItemService.removeOrderItem(Long.parseLong(OrderItemId))) {
			List<Order> orders = orderService.getOrders(Long.parseLong(userId));
			
			return ResponseEntity.ok(orders);
		}
		else {
			return null;
		}
	}

	@DeleteMapping("/{userId}/cancelOrder/{OrderId}")
	public List<Order> deleteOrder(@PathVariable String userId, @PathVariable String OrderId) {
		return orderService.deleteOrderById(Long.parseLong(userId), Long.parseLong(OrderId));
	}

}
