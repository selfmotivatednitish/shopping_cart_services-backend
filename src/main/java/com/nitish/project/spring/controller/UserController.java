package com.nitish.project.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitish.project.spring.modal.Address;
import com.nitish.project.spring.modal.Login;
import com.nitish.project.spring.modal.User;
import com.nitish.project.spring.services.AddressService;
import com.nitish.project.spring.services.JwtService;
import com.nitish.project.spring.services.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	// User Management
	@PostMapping("/login")
	public String getUser(@RequestBody Login login) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(login.getEmail());
		} else {
			throw new UsernameNotFoundException("userNotFound");
		}
	}

	@PostMapping("/signup")
	public User addUser(@RequestBody User user) {
		try {
//			user.setRole(new Role(1));
			user.setRoles("ROLE_USER");
			return this.userService.signupUser(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@GetMapping("/logout")
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

	@GetMapping("/getprofile")
	public User getProfile() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = null;
		if (principal instanceof UserDetails) {
			email = ((UserDetails) principal).getUsername();
		} else {
			email = principal.toString();
		}
		try {
			return this.userService.getProfile(email);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		try {
			return this.userService.updateUser(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// user address management
	@GetMapping("/{userId}/addresses")
	public List<Address> getAddresses(@PathVariable String userId) {
		try {
			return this.addressService.getAddresses(Long.parseLong(userId));
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}

	@PostMapping("/{userId}/addAddress")
	public List<Address> addAddresses(@RequestBody Address address, @PathVariable String userId) {
		try {
			return this.addressService.addAddress(address);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}

	@DeleteMapping("/{userId}/removeAddress/{addressId}")
	public List<Address> deleteAddress(@PathVariable String userId, @PathVariable String addressId) {
		try {
			return addressService.removeAddress(Long.parseLong(userId), Long.parseLong(addressId));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return null;
	}

}
