package com.mbarcina.springmarket.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Product_Delivery")
public class ProductDelivery  implements Serializable {
	
	private static final long serialVersionUID = -5346667547676270478L;

	@Id
	@ManyToOne
	@JoinColumn
	private Product product;
	
	@Id
	@ManyToOne
	@JoinColumn
	private Delivery delivery;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="price")
	private double price;
	
	public ProductDelivery() { }
	
	public ProductDelivery(Product pProduct, Delivery pDelivery, double pPrice, int pQuantity) {
		this.product = pProduct;
		this.delivery = pDelivery;
		this.price = pPrice;	
		this.quantity = pQuantity;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDelivery)) return false;
        ProductDelivery that = (ProductDelivery) o;
        return Objects.equals(product.getId(), that.product.getId()) &&
                Objects.equals(delivery.getId(), that.delivery.getId()) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getId(), delivery.getId(), quantity, price);
    }
	
}

