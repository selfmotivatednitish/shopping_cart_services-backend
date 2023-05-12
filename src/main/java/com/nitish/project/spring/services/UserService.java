package com.nitish.project.spring.services;

import com.nitish.project.spring.modal.User;

public interface UserService {
	public User getProfile(String email);

	public User signupUser(User user);

	public User updateUser(User user);

	public boolean logoutUser(Long id);
}
