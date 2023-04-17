package com.nitish.project.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitish.project.spring.modal.User;

public interface UserDao extends JpaRepository<User, Long> {
	public User findByEmail(String email);
}
