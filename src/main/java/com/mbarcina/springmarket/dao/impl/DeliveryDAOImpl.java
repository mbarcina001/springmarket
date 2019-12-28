package com.mbarcina.springmarket.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IDeliveryDAO;
import com.mbarcina.springmarket.entity.Delivery;
@Repository
public class DeliveryDAOImpl implements IDeliveryDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public Delivery getDeliveryById(int pDeliveryId) {
		// Create a query
		TypedQuery<Delivery> theQuery = entityManager.createQuery("from Delivery WHERE id=" + pDeliveryId, Delivery.class);
		
		// Get the result list
		Delivery delivery = theQuery.getSingleResult();
		
		return delivery;
	}

	@Transactional
	public void saveDelivery(Delivery pDelivery) {
		entityManager.persist(pDelivery);
	}

}
