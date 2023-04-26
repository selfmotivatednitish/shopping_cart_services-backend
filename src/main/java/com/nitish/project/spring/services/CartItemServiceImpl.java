package com.nitish.project.spring.services;

import java.util.List;
import java.util.Optional;

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

	@Override
	public CartItem getCartItem(Long userId, Long productId) {
		return cartItemDao.findByUserIdAndProductId(userId, productId);
	}

	@Override
	public List<CartItem> addCartItem(CartItem cartItem) {
		CartItem cartItem2 = null;
		cartItem2 = getCartItem(cartItem.getUser().getId(), cartItem.getProduct().getId());
		if (cartItem2 != null) {
			cartItem2.setQuantity(cartItem2.getQuantity() + 1);
			cartItemDao.save(cartItem2);
		} else {
			cartItemDao.save(cartItem);
		}
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

	@Override
	public List<CartItem> increaseCartItem(Long cartItemId) {
		Optional<CartItem> optional = cartItemDao.findById(cartItemId);
		CartItem cartItem = optional.orElse(null);
		cartItem.setQuantity(cartItem.getQuantity() + 1);
		cartItemDao.save(cartItem);
		return getAllCartItems(cartItem.getUser().getId());
	}

	@Override
	public List<CartItem> decreaseCartItem(Long cartItemId) {
		Optional<CartItem> optional = cartItemDao.findById(cartItemId);
		CartItem cartItem = optional.orElse(null);
		cartItem.setQuantity(cartItem.getQuantity() - 1);
		cartItemDao.save(cartItem);
		return getAllCartItems(cartItem.getUser().getId());
	}
}
