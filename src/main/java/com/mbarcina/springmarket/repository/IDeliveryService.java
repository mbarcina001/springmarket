package com.mbarcina.springmarket.repository;

import com.mbarcina.springmarket.entity.Delivery;

public interface IDeliveryService {

	public Delivery getDeliveryById(int pDeliveryId);
	public void saveDelivery(Delivery newDelivery);

}
