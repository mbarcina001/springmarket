package com.mbarcina.springmarket.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IDeliveryMethodDAO;
import com.mbarcina.springmarket.entity.DeliveryMethod;
import com.mbarcina.springmarket.repository.IDeliveryMethodService;

@Repository
public class DeliveryMethodServiceImpl implements IDeliveryMethodService {

	@Autowired
	private IDeliveryMethodDAO deliveryMethodDAO;
	
	@Transactional
	public List<DeliveryMethod> getDeliveryMethodList(){
		return deliveryMethodDAO.getDeliveryMethodList();
	}

	@Override
	public DeliveryMethod getDeliveryMethodById(Integer selectedDeliveryMethodId) {
		return deliveryMethodDAO.getDeliveryMethodById(selectedDeliveryMethodId);
	}

}
