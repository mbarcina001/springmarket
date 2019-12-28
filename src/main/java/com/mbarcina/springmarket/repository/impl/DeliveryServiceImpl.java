package com.mbarcina.springmarket.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IDeliveryDAO;
import com.mbarcina.springmarket.entity.Delivery;
import com.mbarcina.springmarket.repository.IDeliveryService;

@Repository
public class DeliveryServiceImpl implements IDeliveryService {
	
	@Autowired
	private IDeliveryDAO deliveryDAO;

	@Override
	public Delivery getDeliveryById(int pDeliveryId) {
		return deliveryDAO.getDeliveryById(pDeliveryId);
	}

	@Override
	public void saveDelivery(Delivery pDeliveryId) {
		deliveryDAO.saveDelivery(pDeliveryId);
	}

}
