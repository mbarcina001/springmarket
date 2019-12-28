package com.mbarcina.springmarket.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IDeliveryMethodDAO;
import com.mbarcina.springmarket.entity.DeliveryMethod;

@Repository
public class DeliveryMethodDAOImpl implements IDeliveryMethodDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public List<DeliveryMethod> getDeliveryMethodList() {		
		// Create a query
		TypedQuery<DeliveryMethod> theQuery = entityManager.createQuery("from DeliveryMethod", DeliveryMethod.class);
		
		// Get the result list
		List<DeliveryMethod> deliveryMethodList = theQuery.getResultList();
		
		return deliveryMethodList;
	}

	@Override
	public DeliveryMethod getDeliveryMethodById(Integer selectedDeliveryMethodId) {
		// Create a query
		TypedQuery<DeliveryMethod> theQuery = entityManager.createQuery("from DeliveryMethod where id="+selectedDeliveryMethodId, DeliveryMethod.class);
		
		// Get the result list
		DeliveryMethod deliveryMethodList = theQuery.getSingleResult();
		
		return deliveryMethodList;
	}

}
