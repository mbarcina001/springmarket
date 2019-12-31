package com.mbarcina.springmarket.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@OneToMany(mappedBy="delivery", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
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
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="user_id")
	private User deliveryUser;
	
	@Column(name="product_total_cost")
	private double productTotalCost;

	@Column(name="delivery_total_cost")
	private double deliveryTotalCost;

	public Delivery() {
		this.productList = new ArrayList<ProductDelivery>();
	}
	
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
	
	public User getDeliveryUser() {
		return deliveryUser;
	}

	public void setDeliveryUser(User deliveryUser) {
		this.deliveryUser = deliveryUser;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", deliveryOrderDate=" + deliveryOrderDate + ", estimatedDeliveryDate="
				+ estimatedDeliveryDate + ", productList=" + productList + ", sendAddress=" + sendAddress
				+ ", billingAccount=" + billingAccount + ", deliveryMethod=" + deliveryMethod + ", deliveryUser="
				+ deliveryUser + ", productTotalCost=" + productTotalCost + ", deliveryTotalCost=" + deliveryTotalCost
				+ "]";
	}

	public void addProduct(ProductDelivery pProduct) {
		this.productList.add(pProduct);
	}
}