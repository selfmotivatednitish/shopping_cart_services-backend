package com.nitish.project.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitish.project.spring.modal.CartItem;
import com.nitish.project.spring.modal.Product;
import com.nitish.project.spring.modal.Quantity;
import com.nitish.project.spring.modal.User;
import com.nitish.project.spring.services.CartItemService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@GetMapping("/{userId}/getCart")
	public List<CartItem> getAllCartItems(@PathVariable String userId) {
		return cartItemService.getAllCartItems(Long.parseLong(userId));
	}

	@GetMapping("/{userId}/getCartItem/{cartItemId}")
	public CartItem getCartItem(@PathVariable String userId, @PathVariable String cartItemId) {
		return cartItemService.getCartItem(Long.parseLong(userId), Long.parseLong(cartItemId));
	}

	@GetMapping("/{userId}/add/{productId}/{quantity}")
	public List<CartItem> addCartItem(@PathVariable String userId, @PathVariable String productId, @PathVariable String quantity) {
		CartItem cartItem = new CartItem(Long.parseLong(quantity), new Product(Long.parseLong(productId)), new User(Long.parseLong(userId)));
		return cartItemService.addCartItem(cartItem);
	}

	@PutMapping("Item/{cartItemId}/incrquant")
	public List<CartItem> incrItem(@PathVariable String cartItemId) {
		return cartItemService.increaseCartItem(Long.parseLong(cartItemId));
	}

	@PutMapping("Item/{cartItemId}/decrquant")
	public List<CartItem> decrItem(@PathVariable String cartItemId) {
		return cartItemService.decreaseCartItem(Long.parseLong(cartItemId));
	}

	@DeleteMapping("/{userId}/remove/{productId}")
	public boolean removeCartItem(@PathVariable String userId, @PathVariable String productId) {
		return cartItemService.removeCartItem(Long.parseLong(userId), Long.parseLong(productId));
	}

	@PostMapping("cart/{userId}/changeQuantity/{productId}")
	public CartItem changeQuantityCartItem(@RequestBody Quantity quantity, @PathVariable String userId,
			@PathVariable String productId) {
		return cartItemService.changeQuantityCartItem(quantity.getQuantity(), Long.parseLong(userId),
				Long.parseLong(productId));
	}
}
