package com.nitish.project.spring.services;

import java.util.List;

import com.nitish.project.spring.modal.CartItem;

public interface CartItemService {
	public List<CartItem> getAllCartItems(Long userId);

	public CartItem getCartItem(Long userId, Long cartItemId);

	public List<CartItem> addCartItem(CartItem cartItem);

	public CartItem changeQuantityCartItem(Long userId, Long productId, Long quantity);
	
	public boolean removeCartItem(Long userId, Long productId);
	
	public List<CartItem> increaseCartItem(Long cartItemId);
	
	public List<CartItem> decreaseCartItem(Long cartItemId);
}
