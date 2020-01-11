package com.mbarcina.springmarket.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="User")
public class User{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;

	@NotNull
	@NotBlank
	@Column(name="name")
	private String name;
	
	@Transient
    private String oldPassword;
	
	@NotNull
	@Column(name="password")
	private String password;
	
	@Transient
    private String retypePassword;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "\\w+@\\w+\\.\\w+(,\\s*\\w+@\\w+\\.\\w+)*")
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
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<UserRole> roles = new ArrayList<>();

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

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
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
	
	public void addRole(UserRole pRole) {
		this.roles.add(pRole);
	}
	
	public void addCreditCard(CreditCard pCreditCard) {
		this.cardList.add(pCreditCard);
	}
	
	public void addDelivery(Delivery pDelivery) {
		this.deliveryList.add(pDelivery);
	}
	
	public void deleteAddress(Address pAddress) {
		this.addressList.remove(pAddress);
	}
	
	public void deleteCreditCard(CreditCard pCreditCard) {
		this.cardList.remove(pCreditCard);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", enabled="
				+ enabled + ", deliveryList=" + deliveryList + ", addressList=" + addressList + ", cardList=" + cardList
				+ ", roles=" + roles + "]";
	}
	
}

