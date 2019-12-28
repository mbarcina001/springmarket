package com.mbarcina.springmarket.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="User")
public class User{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@NotNull
	@Column(name="password")
	private String password;
	
	@NotNull
	@Column(name="email")
	private String email;
	
	@Column(name = "enabled")
    private int enabled;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<Delivery> deliveryList;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<Address> addressList;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<CreditCard> cardList;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

	public User(){  }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Delivery> getDeliveryList() {
		return deliveryList;
	}

	public void setDeliveryList(List<Delivery> deliveryList) {
		this.deliveryList = deliveryList;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public List<CreditCard> getCardList() {
		return cardList;
	}

	public void setCardList(List<CreditCard> cardList) {
		this.cardList = cardList;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	public void addAddress(Address pAddress) {
		this.addressList.add(pAddress);
	}
	
	public void addCreditCard(CreditCard pCreditCard) {
		this.cardList.add(pCreditCard);
	}
	
	public void addDelivery(Delivery pDelivery) {
		System.out.println("addDelivery");
		System.out.println(pDelivery);
		this.deliveryList.add(pDelivery);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", enabled="
				+ enabled + ", deliveryList=" + deliveryList + ", addressList=" + addressList + ", cardList=" + cardList
				+ ", roles=" + roles + "]";
	}
	
}

