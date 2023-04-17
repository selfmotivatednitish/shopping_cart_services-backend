package com.nitish.project.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitish.project.spring.dao.CartItemDao;
import com.nitish.project.spring.modal.CartItem;

@Service
public class CartItemServiceImpl implements CartItemService {
	
	@Autowired
	private CartItemDao cartItemDao;

	@Override
	public List<CartItem> getAllCartItems(Long userId) {
		return cartItemDao.findAllByUserId(userId);
	}

	@SuppressWarnings("deprecation")
	@Override
	public CartItem getCartItem(Long userId, Long cartItemId) {
		return cartItemDao.getById(cartItemId);
	}

	@Override
	public List<CartItem> addCartItem(CartItem cartItem) {
		cartItemDao.save(cartItem);
		return getAllCartItems(cartItem.getUser().getId());
	}

	@Override
	public boolean removeCartItem(Long userId, Long productId) {
		cartItemDao.deleteByUserIdAndProductId(userId, productId);
		return false;
	}

	@Override
	public CartItem changeQuantityCartItem(Long quantity, Long userId, Long productId) {
		CartItem cartItem = cartItemDao.findByUserIdAndProductId(userId, productId);
		cartItem.setQuantity(cartItem.getQuantity() + quantity);
		return cartItem;
	}
}
