package com.nitish.project.spring.services;

import java.util.List;

import com.nitish.project.spring.modal.Address;

public interface AddressService {
	public List<Address> getAddresses(Long userId);

	public List<Address> addAddress(Address address);

	public List<Address> updateAddress(Address address);

	public boolean removeAddress(Long addressId);
}
