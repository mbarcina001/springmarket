package com.mbarcina.springmarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Address")
public class Address{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_id")
	private int id;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@NotNull
	@Column(name="phone")
	private String phone;
	
	@NotNull
	@Column(name="country")
	private String country;
	
	@NotNull
	@Column(name="city")
	private String city;
	
	@NotNull
	@Column(name="province")
	private String province;
	
	@NotNull
	@Column(name="address")
	private String address;
	
	@NotNull
	@Size(max=5, min=5)
	@Column(name="zipcode")
	private String zipcode;
	
	public Address(){  }

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", phone=" + phone + ", country=" + country + ", city=" + city
				+ ", province=" + province + ", address=" + address + ", zipcode=" + zipcode + "]";
	}

}