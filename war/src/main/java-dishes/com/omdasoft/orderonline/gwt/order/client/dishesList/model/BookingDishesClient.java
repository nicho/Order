package com.omdasoft.orderonline.gwt.order.client.dishesList.model;

import java.io.Serializable;
import java.util.List;

public class BookingDishesClient implements Serializable, Comparable<BookingDishesClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;
	
	private String id;
	private String name;
	private int number;	
	private String unit;	
	private String taste;	
	private String price;
	private String unitprice;
	private List<String> tasteList;
	
	public List<String> getTasteList() {
		return tasteList;
	}
	public void setTasteList(List<String> tasteList) {
		this.tasteList = tasteList;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
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
	public BookingDishesClient() {

	}
	@Override
	public int compareTo(BookingDishesClient o) {
		// TODO Auto-generated method stub
		return 0;
	}



}
