package com.omdasoft.orderonline.domain.org;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "restaurant")
public class Restaurant extends Organization {

	private static final long serialVersionUID = -2314873131853974603L;

	private Corporation corp;
	private String city;
	private String address;
	private String phone;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Corporation getCorp() {
		return corp;
	}

	public void setCorp(Corporation corp) {
		this.corp = corp;
	}

}


