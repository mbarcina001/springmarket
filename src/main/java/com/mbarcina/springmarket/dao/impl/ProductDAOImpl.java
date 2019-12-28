package com.mbarcina.springmarket.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IProductDAO;
import com.mbarcina.springmarket.entity.Product;

@Repository
public class ProductDAOImpl implements IProductDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public List<Product> getProductList() {		
		// Create a query
		TypedQuery<Product> theQuery = entityManager.createQuery("from Product", Product.class);
		
		// Get the result list
		List<Product> products = theQuery.getResultList();
		
		return products;
	}

	@Override
	public Product getProductList(int pIdProduct) {
		// Create a query
		TypedQuery<Product> theQuery = entityManager.createQuery("from Product WHERE id=" + pIdProduct, Product.class);
		
		// Get the result list
		Product product = theQuery.getSingleResult();
		
		return product;
	}
	
}
