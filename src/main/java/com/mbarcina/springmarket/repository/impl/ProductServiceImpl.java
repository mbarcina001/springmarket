package com.mbarcina.springmarket.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IProductDAO;
import com.mbarcina.springmarket.entity.Product;
import com.mbarcina.springmarket.repository.IProductService;

@Repository
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	private IProductDAO productDAO;
	
	@Transactional
	public List<Product> getProductList(){
		return productDAO.getProductList();
	}

	@Override
	public Product getProduct(int pIdProduct) {
		return productDAO.getProductList(pIdProduct);
	}

	@Override
	public List<Product> searchProduct(String pSearchTerm) {
		return productDAO.searchProductList(pSearchTerm);
	}

}
