package com.mbarcina.springmarket.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IRoleDAO;
import com.mbarcina.springmarket.entity.Role;

@Repository
public class RoleDAOImpl implements IRoleDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Role findByRole(String pRole) {
		TypedQuery<Role> theQuery = entityManager.createQuery("from Role where role = :role", Role.class);
		theQuery.setParameter("role", pRole);
		
		// Get the result list
		try {
			Role role = theQuery.getSingleResult();
			return role;
		}catch(NoResultException e) {
			System.err.println("No role found with role: " + pRole);
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
