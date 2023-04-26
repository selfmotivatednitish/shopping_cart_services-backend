package com.nitish.project.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nitish.project.spring.modal.Address;
import com.nitish.project.spring.modal.CartItem;
import com.nitish.project.spring.modal.Login;
import com.nitish.project.spring.modal.Order;
import com.nitish.project.spring.modal.OrderItem;
import com.nitish.project.spring.modal.Product;
import com.nitish.project.spring.modal.Quantity;
import com.nitish.project.spring.modal.User;
import com.nitish.project.spring.services.AddressService;
import com.nitish.project.spring.services.CartItemService;
import com.nitish.project.spring.services.OrderItemService;
import com.nitish.project.spring.services.OrderService;
import com.nitish.project.spring.services.ProductService;
import com.nitish.project.spring.services.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MyController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	@GetMapping("/home")
	public String home() {
		return "this is home page";
	}

	// Product Management

	// get all the products
	@GetMapping("/products")
	public List<Product> getProducts() {
		return this.productService.getProducts();
	}

	// add product

	@PostMapping("/products/addProduct")
	public Product addProduct(@RequestBody Product product) {
		return this.productService.addProduct(product);
	}

	// update product
	@PutMapping("/products/update")
	public Product updateProduct(@RequestBody Product product) {
		return this.productService.updateProduct(product);
	}

	// get single product
	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable String productId) {
		return this.productService.getProduct(Long.parseLong(productId));
	}

	@GetMapping("/products/category/{category}")
	public List<Product> getProductsByCategory(@PathVariable String category) {
		return this.productService.getProductsByCategory(category);
	}

	// get product by search string
	@GetMapping("products/search/{searchString}")
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
	@DeleteMapping("/products/getById/{productId}")
	public ResponseEntity<HttpStatus> removeProduct(@PathVariable String productId) {
		try {
			this.productService.removeProduct(Long.parseLong(productId));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// User Management
	@PostMapping("user/login")
	public User getUser(@RequestBody Login login) {
		try {
			User user = this.userService.loginUser(login.getEmail(), login.getPassword());
			for (int i = 0; i < user.getAddresses().size(); i++) {
				user.getAddresses().get(i).setUser(null);
			}
			return user;
		} catch (Exception e) {

		}
		return null;
	}

	@PostMapping("user/signup")
	public User addUser(@RequestBody User user) {
		try {
			return this.userService.signupUser(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@GetMapping("user/logout")
	public boolean logoutUser(@RequestBody String userId) {
		try {
			this.userService.logoutUser(Long.parseLong(userId));
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		}
		return false;
	}

	@GetMapping("user/getprofile/{userId}")
	public User getProfile(@PathVariable String userId) {
		try {
			return this.userService.getProfile(Long.parseLong(userId));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@PutMapping("user/update")
	public User updateUser(@RequestBody User user) {
		try {
			return this.userService.updateUser(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// user address management
	@GetMapping("/user/{userId}/addresses")
	public List<Address> getAddresses(@PathVariable String userId) {
		try {
			return this.addressService.getAddresses(Long.parseLong(userId));
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}

	@PostMapping("/user/{userId}/addAddress")
	public List<Address> addAddresses(@RequestBody Address address, @PathVariable String userId) {
		try {
			return this.addressService.addAddress(address);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}

	@DeleteMapping("/user/{userId}/removeAddress/{addressId}")
	public List<Address> deleteAddress(@PathVariable String userId, @PathVariable String addressId) {
		try {
			return addressService.removeAddress(Long.parseLong(userId), Long.parseLong(addressId));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return null;
	}

	@GetMapping("/cart/{userId}/getCart")
	public List<CartItem> getAllCartItems(@PathVariable String userId) {
		return cartItemService.getAllCartItems(Long.parseLong(userId));
	}

	@GetMapping("/cart/{userId}/getCartItem/{cartItemId}")
	public CartItem getCartItem(@PathVariable String userId, @PathVariable String cartItemId) {
		return cartItemService.getCartItem(Long.parseLong(userId), Long.parseLong(cartItemId));
	}

	@GetMapping("/cart/{userId}/add/{productId}")
	public List<CartItem> addCartItem(@PathVariable String userId, @PathVariable String productId) {
		CartItem cartItem = new CartItem(1, new Product(Long.parseLong(productId)), new User(Long.parseLong(userId)));
		return cartItemService.addCartItem(cartItem);
	}

	@PutMapping("/cart/cartItem/{cartItemId}/incrquant")
	public List<CartItem> incrItem(@PathVariable String cartItemId) {
		return cartItemService.increaseCartItem(Long.parseLong(cartItemId));
	}

	@PutMapping("/cart/cartItem/{cartItemId}/decrquant")
	public List<CartItem> decrItem(@PathVariable String cartItemId) {
		return cartItemService.decreaseCartItem(Long.parseLong(cartItemId));
	}

	@DeleteMapping("/cart/{userId}/remove/{productId}")
	public boolean removeCartItem(@PathVariable String userId, @PathVariable String productId) {
		return cartItemService.removeCartItem(Long.parseLong(userId), Long.parseLong(productId));
	}

	@PostMapping("cart/{userId}/changeQuantity/{productId}")
	public CartItem changeQuantityCartItem(@RequestBody Quantity quantity, @PathVariable String userId,
			@PathVariable String productId) {
		return cartItemService.changeQuantityCartItem(quantity.getQuantity(), Long.parseLong(userId),
				Long.parseLong(productId));
	}

	@GetMapping("/order/{userId}/getOrders")
	public List<Order> getOrders(@PathVariable String userId) {
		return orderService.getOrders(Long.parseLong(userId));
	}
	
	

	@PostMapping("/order/{userId}/createOrder")
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
	
	@PostMapping("/order/{userId}/checkout")
	public ResponseEntity<List<Order>> checkoutCart(@PathVariable String userId) {
		List<CartItem> cartItems = getAllCartItems(userId);
		Order order = new Order();
		order.setUser((new User(Long.parseLong(userId))));
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem cartItem : cartItems) {
			removeCartItem(userId, "" + cartItem.getProduct().getId());
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
	
	@DeleteMapping("/order/{userId}/cancelOrderItem/{OrderItemId}")
	public ResponseEntity<List<Order>> deleteOrderItem(@PathVariable String userId, @PathVariable String OrderItemId) {
		if(orderItemService.removeOrderItem(Long.parseLong(OrderItemId))) {
			List<Order> orders = orderService.getOrders(Long.parseLong(userId));
			
			return ResponseEntity.ok(orders);
		}
		else {
			return null;
		}
	}

	@DeleteMapping("/order/{userId}/cancelOrder/{OrderId}")
	public List<Order> deleteOrder(@PathVariable String userId, @PathVariable String OrderId) {
		return orderService.deleteOrderById(Long.parseLong(userId), Long.parseLong(OrderId));
	}
}
