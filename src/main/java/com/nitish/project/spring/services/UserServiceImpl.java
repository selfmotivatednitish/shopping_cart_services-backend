package com.nitish.project.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nitish.project.spring.dao.UserDao;
import com.nitish.project.spring.modal.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User signupUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User signupUser = userDao.findByEmail(user.getEmail());
		if(signupUser == null) {
			signupUser = userDao.save(user);
		}
		else {
			return null;
		}
		signupUser.setPassword("");
		
		return signupUser;
	}

	@Override
	public User updateUser(User user) {
		User updateUser = userDao.findByEmail(user.getEmail());
		if(updateUser.getPassword().equals(updateUser.getPassword())) {
			updateUser = userDao.save(user);
		}
		else {
			return null;
		}
		updateUser.setPassword("");
		return updateUser;
	}

	@Override
	public User getProfile(String email) {
		User loginUser = userDao.findByEmail(email);
		if(loginUser != null) {
			loginUser.setPassword("");
		}
		return loginUser;
	}

	@Override
	public boolean logoutUser(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
