package com.omdasoft.orderonline.gwt.order.client.dishesList.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BookingDishesList  implements IsSerializable {


	private String id;
	private String name;
	private int number;	
	private String unit;	
	private String taste;	
	private String price;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public BookingDishesList() {

	}




}
