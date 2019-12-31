package com.mbarcina.springmarket.repository;

import com.mbarcina.springmarket.entity.Address;

public interface IAddressService {
	
	public void saveAddress(Address pAddress);
	public void deleteAddress(int pAddressId);
	public Address getAddressById(Integer selectedAddressId);
	public void updateAddress(Address pAddress);

}
