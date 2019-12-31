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
	public void saveAddress(Address pAddress) {
		entityManager.persist(pAddress);
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
