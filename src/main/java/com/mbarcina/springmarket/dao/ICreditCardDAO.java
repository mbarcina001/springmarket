package com.mbarcina.springmarket.dao;

import com.mbarcina.springmarket.entity.CreditCard;

public interface ICreditCardDAO {
	
	public void saveCreditCard(CreditCard pCreditCard);
	public void deleteCreditCard(int pCreditCardId);
	public CreditCard getCreditCardById(Integer pCreditCardId);

}
