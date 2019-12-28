package com.mbarcina.springmarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Credit_Card")
public class CreditCard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="credit_card_id")
	private int id;
	
	@NotNull
	@Column(name="holder")
	private String holder;
	
	@NotNull
	@Column(name="number")
	private String number;
	
	@NotNull
	@Max(value=12)
	@Min(value=1)
	@Column(name="expiration_date_month")
	private Integer expirationDateMonth;
	
	@NotNull
	@Max(value=2030)
	@Min(value=2019)
	@Column(name="expiration_date_year")
	private Integer expirationDateYear;
	
	@NotNull
	@Column(name="cvc")
	@Size(max=3, min=1)
	private String cvc;
	
	public CreditCard(){  }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getExpirationDateMonth() {
		return expirationDateMonth;
	}

	public void setExpirationDateMonth(Integer expirationDateMonth) {
		this.expirationDateMonth = expirationDateMonth;
	}

	public Integer getExpirationDateYear() {
		return expirationDateYear;
	}

	public void setExpirationDateYear(Integer expirationDateYear) {
		this.expirationDateYear = expirationDateYear;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", holder=" + holder + ", number=" + number + ", expirationDateMonth="
				+ expirationDateMonth + ", expirationDateYear=" + expirationDateYear + ", cvc=" + cvc + "]";
	}

}

