package com.mbarcina.springmarket.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Delivery")
public class Delivery{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="delivery_id")
	private int id;
	
	@Column(name="delivery_order_date")
	private Date deliveryOrderDate;
	
	@Column(name="estimated_delivery_date")
	private Date estimatedDeliveryDate;
	
	@OneToMany(mappedBy="delivery", cascade=CascadeType.ALL)
	private List<ProductDelivery> productList = new ArrayList<>();
	
	@OneToOne()
	@JoinColumn(name="address_id")
	private Address sendAddress;
	
	@OneToOne()
	@JoinColumn(name="credit_card_id")
	private CreditCard billingAccount;
	
	@OneToOne()
	@JoinColumn(name="delivery_method_id")
	private DeliveryMethod deliveryMethod;
	
	@Column(name="product_total_cost")
	private double productTotalCost;
	
	public double getProductTotalCost() {
		return productTotalCost;
	}

	public void setProductTotalCost(double productTotalCost) {
		this.productTotalCost = productTotalCost;
	}

	public double getDeliveryTotalCost() {
		return deliveryTotalCost;
	}

	public void setDeliveryTotalCost(double deliveryTotalCost) {
		this.deliveryTotalCost = deliveryTotalCost;
	}

	@Column(name="delivery_total_cost")
	private double deliveryTotalCost;

	public Delivery() {
		this.productList = new ArrayList<ProductDelivery>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDeliveryOrderDate() {
		return deliveryOrderDate;
	}

	public void setDeliveryOrderDate(Date deliveryOrderDate) {
		this.deliveryOrderDate = deliveryOrderDate;
	}

	public Date getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public List<ProductDelivery> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDelivery> productList) {
		this.productList = productList;
	}

	public Address getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(Address sendAddress) {
		this.sendAddress = sendAddress;
	}

	public CreditCard getBillingAccount() {
		return billingAccount;
	}

	public void setBillingAccount(CreditCard billingAccount) {
		this.billingAccount = billingAccount;
	}

	public DeliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", deliveryOrderDate=" + deliveryOrderDate + ", estimatedDeliveryDate="
				+ estimatedDeliveryDate + ", productList=" + productList + ", sendAddress=" + sendAddress
				+ ", billingAccount=" + billingAccount + "]";
	}
	
	public void addProduct(ProductDelivery pProduct) {
		this.productList.add(pProduct);
	}
	
	/*public void addProduct(Product product, int pPrice, int pQuantity) {
        ProductDelivery productDelivery = new ProductDelivery(product, this, pPrice, pQuantity);
        this.productList.add(productDelivery);
    }*/
 
    /*public void removeTag(Tag tag) {
        for (Iterator<PostTag> iterator = tags.iterator();
             iterator.hasNext(); ) {
            PostTag postTag = iterator.next();
 
            if (postTag.getPost().equals(this) &&
                    postTag.getTag().equals(tag)) {
                iterator.remove();
                postTag.getTag().getPosts().remove(postTag);
                postTag.setPost(null);
                postTag.setTag(null);
            }
        }
    }*/

}