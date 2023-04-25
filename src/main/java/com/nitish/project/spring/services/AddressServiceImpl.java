package com.nitish.project.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitish.project.spring.dao.AddressDao;
import com.nitish.project.spring.modal.Address;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDao addressDao;
	
	@Override
	public List<Address> getAddresses(Long userId) {
		return addressDao.findAllByUserId(userId);
	}

	@Override
	public List<Address> addAddress(Address address) {
		addressDao.save(address);
		System.out.println(address.getUser());
		return getAddresses(address.getUser().getId());
	}

	@Override
	public List<Address> updateAddress(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> removeAddress(Long userId, Long addressId) {
		addressDao.deleteById(addressId);
		return getAddresses(userId);
	}
}
