package com.nitish.project.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitish.project.spring.dao.UserDao;
import com.nitish.project.spring.modal.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User loginUser(String email, String password) {
		User loginUser = userDao.findByEmail(email);
		if(loginUser != null && loginUser.getPassword() == password) {
			loginUser.setPassword("");
		}
		return loginUser;
	}

	@Override
	public User signupUser(User user) {
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

	@SuppressWarnings("deprecation")
	@Override
	public User getProfile(Long id) {
		return userDao.getById(id);
	}

	@Override
	public boolean logoutUser(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
