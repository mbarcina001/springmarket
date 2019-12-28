package com.mbarcina.springmarket.classes;

import java.io.Serializable;

import com.mbarcina.springmarket.entity.Delivery;
import com.mbarcina.springmarket.entity.Product;

public class ProductOrderPK implements Serializable {
	private static final long serialVersionUID = 6361645933292381095L;
	private Product product;
	private Delivery delivery;
 
	public ProductOrderPK() { }
	
    public ProductOrderPK(Product pProduct, Delivery pDelivery) {
        this.product = pProduct;
        this.delivery = pDelivery;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
}