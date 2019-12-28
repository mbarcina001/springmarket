package com.mbarcina.springmarket.repository;

import java.util.List;

import com.mbarcina.springmarket.entity.DeliveryMethod;

public interface IDeliveryMethodService {
	
	public List<DeliveryMethod> getDeliveryMethodList();
	public DeliveryMethod getDeliveryMethodById(Integer selectedDeliveryMethodId);

}
