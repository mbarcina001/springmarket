package com.mbarcina.springmarket.dao;

import java.util.List;

import com.mbarcina.springmarket.entity.DeliveryMethod;

public interface IDeliveryMethodDAO {
	
	public List<DeliveryMethod> getDeliveryMethodList();
	public DeliveryMethod getDeliveryMethodById(Integer selectedDeliveryMethodId);

}
