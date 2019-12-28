package com.mbarcina.springmarket.repository;

import java.util.List;

import com.mbarcina.springmarket.entity.Product;

public interface IProductService {
	
	public List<Product> getProductList();
	public Product getProduct(int pIdProduct);

}
