package com.mbarcina.springmarket.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IAddressDAO;
import com.mbarcina.springmarket.entity.Address;
import com.mbarcina.springmarket.repository.IAddressService;

@Repository
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private IAddressDAO addressDAO;

	@Override
	public void saveAddress(Address pAddress, int pCustomerId) {
		addressDAO.saveAddress(pAddress, pCustomerId);
	}

	@Override
	public void updateAddress(Address pAddress) {
		addressDAO.updateAddress(pAddress);
	}

	@Override
	public void deleteAddress(int pAddressId) {
		addressDAO.deleteAddress(pAddressId);
	}

	@Override
	public Address getAddressById(Integer pAddressId) {
		return addressDAO.getAddressById(pAddressId);
	}

}
