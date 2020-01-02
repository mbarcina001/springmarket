package com.mbarcina.springmarket.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.ICreditCardDAO;
import com.mbarcina.springmarket.entity.CreditCard;

@Repository
public class CreditCardDAOImpl implements ICreditCardDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void saveCreditCard(CreditCard pCreditCard) {
		entityManager.persist(pCreditCard);
	}
	
	@Transactional
	public void updateCreditCard(CreditCard pCreditCard){
		entityManager.createQuery("UPDATE CreditCard SET holder=?1, number=?2, expiration_date_month=?3, expiration_date_year=?4, cvc=?5 WHERE credit_card_id=?6")
	      .setParameter(1, pCreditCard.getHolder())
	      .setParameter(2, pCreditCard.getNumber())
	      .setParameter(3, pCreditCard.getExpirationDateMonth())
	      .setParameter(4, pCreditCard.getExpirationDateYear())
	      .setParameter(5, pCreditCard.getCvc())
	      .setParameter(6, pCreditCard.getId())
	      .executeUpdate();
	}

	@Transactional
	public void deleteCreditCard(int pCreditCardId) {
		entityManager.createQuery("DELETE FROM CreditCard where id=" + pCreditCardId).executeUpdate();
	}
	
	@Override
	public CreditCard getCreditCardById(Integer pCreditCardId) {
		TypedQuery<CreditCard> theQuery = entityManager.createQuery("from CreditCard where id=" + pCreditCardId, CreditCard.class);
		
		// Get the result list
		CreditCard card = theQuery.getSingleResult();
		
		return card;
	}
	
	

}
