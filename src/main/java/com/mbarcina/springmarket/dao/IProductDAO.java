package com.mbarcina.springmarket.dao;

import java.util.List;

import com.mbarcina.springmarket.entity.Product;

public interface IProductDAO {
	
	public List<Product> getProductList();
	public Product getProductList(int pIdProduct);
	public List<Product> searchProductList(String pSearchTerm);

}
