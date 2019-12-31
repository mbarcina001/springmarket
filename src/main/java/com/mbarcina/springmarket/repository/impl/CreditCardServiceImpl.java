package com.mbarcina.springmarket.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.ICreditCardDAO;
import com.mbarcina.springmarket.entity.CreditCard;
import com.mbarcina.springmarket.repository.ICreditCardService;

@Repository
public class CreditCardServiceImpl implements ICreditCardService {
	
	@Autowired
	private ICreditCardDAO creditCardDAO;

	@Override
	public void saveCreditCard(CreditCard pCreditCard) {
		creditCardDAO.saveCreditCard(pCreditCard);
	}

	@Override
	public void updateCreditCard(CreditCard pCreditCard) {
		creditCardDAO.updateCreditCard(pCreditCard);
	}
	
	@Override
	public void deleteCreditCard(int pCreditCardId) {
		creditCardDAO.deleteCreditCard(pCreditCardId);
	}

	@Override
	public CreditCard getCreditCardById(Integer pCreditCardId) {
		return creditCardDAO.getCreditCardById(pCreditCardId);
	}

}
