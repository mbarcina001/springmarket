package com.mbarcina.springmarket.dao;

import com.mbarcina.springmarket.entity.Delivery;

public interface IDeliveryDAO {

	public Delivery getDeliveryById(int pDeliveryId);
	public void saveDelivery(Delivery pDeliveryId);

}
