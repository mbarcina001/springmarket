package com.mbarcina.springmarket.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IUserDAO;
import com.mbarcina.springmarket.entity.User;

@Repository
public class UserDAOImpl implements IUserDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public User findUserById(int pId) {
		// Create a query
		TypedQuery<User> theQuery = entityManager.createQuery("from User where id = :userId", User.class);
		theQuery.setParameter("userId", pId);
		
		// Get the result list
		try {
			User user = theQuery.getSingleResult();
			return user;
		}catch(NoResultException e) {
			System.err.println("No user found with id: " + pId);
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public User findUserByEmail(String pEmail) {
		// Create a query
		TypedQuery<User> theQuery = entityManager.createQuery("from User where email = :userEmail", User.class);
		theQuery.setParameter("userEmail", pEmail);
		
		// Get the result list
		try {
			User user = theQuery.getSingleResult();
			return user;
		}catch(NoResultException e) {
			System.err.println("No user found with email: " + pEmail);
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	@Transactional
	public void saveUser(User pUser) {
		entityManager.persist(pUser);
	}
}
