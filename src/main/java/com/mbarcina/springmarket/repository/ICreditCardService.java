package com.mbarcina.springmarket.repository;

import com.mbarcina.springmarket.entity.CreditCard;

public interface ICreditCardService {
	
	public void saveCreditCard(CreditCard pCreditCard);
	public void deleteCreditCard(int pCreditCardId);
	public CreditCard getCreditCardById(Integer selectedAddressId);
	public void updateCreditCard(CreditCard pCreditCard);

}
