package com.mbarcina.springmarket.repository;

import com.mbarcina.springmarket.entity.CreditCard;

public interface ICreditCardService {
	
	public void saveCreditCard(CreditCard pCreditCard, int pCustomerId);
	public void updateCreditCard(CreditCard pCreditCard);
	public void deleteCreditCard(int pCreditCardId);
	public CreditCard getCreditCardById(Integer selectedAddressId);

}
