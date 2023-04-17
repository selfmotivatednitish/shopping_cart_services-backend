package com.nitish.project.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitish.project.spring.modal.Address;

public interface AddressDao extends JpaRepository<Address, Long> {
	public List<Address> findAllByUserId(Object userId);
}
