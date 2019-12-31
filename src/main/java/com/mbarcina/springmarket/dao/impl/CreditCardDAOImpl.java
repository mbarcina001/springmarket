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
	public void deleteCreditCard(int pCreditCardId) {
		entityManager.createQuery("DELETE FROM credit_card where id=" + pCreditCardId);
	}
	
	@Override
	public CreditCard getCreditCardById(Integer pCreditCardId) {
		TypedQuery<CreditCard> theQuery = entityManager.createQuery("from CreditCard where id=" + pCreditCardId, CreditCard.class);
		
		// Get the result list
		CreditCard card = theQuery.getSingleResult();
		
		return card;
	}

}
