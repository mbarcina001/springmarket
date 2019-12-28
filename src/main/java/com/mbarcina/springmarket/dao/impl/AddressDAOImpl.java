package com.mbarcina.springmarket.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IAddressDAO;
import com.mbarcina.springmarket.entity.Address;

@Repository
public class AddressDAOImpl implements IAddressDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void saveAddress(Address pAddress, int pCustomerId) {
		// Create a query
		entityManager.createQuery("INSERT INTO address (name, phone, country, city, province, address, zipcode, customer_id) VALUES (?,?,?,?,?,?,?,?)")
	      .setParameter(1, pAddress.getName())
	      .setParameter(2, pAddress.getPhone())
	      .setParameter(3, pAddress.getCountry())
	      .setParameter(4, pAddress.getCity())
	      .setParameter(5, pAddress.getProvince())
	      .setParameter(6, pAddress.getAddress())
	      .setParameter(7, pAddress.getZipcode())
	      .setParameter(8, pCustomerId)
	      .executeUpdate();
	}

	@Transactional
	public void updateAddress(Address pAddress) {
		// Create a query
		// TypedQuery<Product> theQuery = entityManager.createQuery("from Product", Product.class);

	}

	@Transactional
	public void deleteAddress(int pAddressId) {
		// Create a query
		entityManager.createQuery("DELETE FROM address where id=" + pAddressId);
	}

	@Override
	public Address getAddressById(Integer pAddressId) {
		TypedQuery<Address> theQuery = entityManager.createQuery("from Address where id="+pAddressId, Address.class);
		
		// Get the result list
		Address address = theQuery.getSingleResult();
		
		return address;
	}

}
