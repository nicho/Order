package com.omdasoft.orderonline.gwt.order.client.orderView.model;

import java.io.Serializable;

public class OrderDishesListClient implements Serializable, Comparable<OrderDishesListClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;
	private String id;
	private String number;
	private String dishesName;
	private String dishesType;
	private String price;
	private String totalprice;
	private String photo;
	private String unit;
	private String taste;
	
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
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDishesName() {
		return dishesName;
	}
	public void setDishesName(String dishesName) {
		this.dishesName = dishesName;
	}
	public String getDishesType() {
		return dishesType;
	}
	public void setDishesType(String dishesType) {
		this.dishesType = dishesType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public int compareTo(OrderDishesListClient o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
