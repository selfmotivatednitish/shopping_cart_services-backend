package com.nitish.project.spring.services;

import com.nitish.project.spring.modal.User;

public interface UserService {
	public User getProfile(Long id);
	public User loginUser(String email, String Password);
	public User signupUser(User user);
	public User updateUser(User user);
	public boolean logoutUser(Long id);
}
