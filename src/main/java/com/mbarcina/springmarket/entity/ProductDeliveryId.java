package com.mbarcina.springmarket.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductDeliveryId implements Serializable{

	private static final long serialVersionUID = -1580464256082323017L;
	
	@Column(name="product_id")
	private int productId;
	
	@Column(name="delivery_id")
	private int deliveryId;
	
	public ProductDeliveryId() { }
	
	public ProductDeliveryId(int pProductId, int pDeliveryId) {
		this.productId = pProductId;
		this.deliveryId = pDeliveryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        ProductDeliveryId that = (ProductDeliveryId) o;
        return Objects.equals(productId, that.productId) &&
               Objects.equals(deliveryId, that.deliveryId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(productId, deliveryId);
    }

}
