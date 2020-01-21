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
	public void updateAddress(Address pAddress) {
		entityManager.createQuery("UPDATE Address SET name=?1, phone=?2, country=?3, city=?4, province=?5, address=?6, zipcode=?7 WHERE id = ?8")
	      .setParameter(1, pAddress.getName())
	      .setParameter(2, pAddress.getPhone())
	      .setParameter(3, pAddress.getCountry())
	      .setParameter(4, pAddress.getCity())
	      .setParameter(5, pAddress.getProvince())
	      .setParameter(6, pAddress.getAddress())
	      .setParameter(7, pAddress.getZipcode())
	      .setParameter(8, pAddress.getId())
	      .executeUpdate();
	}

	@Transactional
	public void deleteAddress(int pAddressId) {
		entityManager.createQuery("DELETE FROM Address where id=" + pAddressId).executeUpdate();
	}

	@Override
	public Address getAddressById(Integer pAddressId) {
		TypedQuery<Address> theQuery = entityManager.createQuery("from Address where id="+pAddressId, Address.class);
		
		// Get the result list
		Address address = theQuery.getSingleResult();
		
		return address;
	}

}
