package com.mbarcina.springmarket.dao;

import com.mbarcina.springmarket.entity.Address;

public interface IAddressDAO {
	
	public void saveAddress(Address pAddress);
	public void deleteAddress(int pAddressId);
	public Address getAddressById(Integer pAddressId);
	public void updateAddress(Address pAddress);

}
