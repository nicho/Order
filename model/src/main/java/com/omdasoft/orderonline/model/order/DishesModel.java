package com.omdasoft.orderonline.model.order;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class DishesModel  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dishesId;
	private String dishesName;
	private String dishesType;
	private int number;
	private double price;
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

	public String getDishesId() {
		return dishesId;
	}
	public void setDishesId(String dishesId) {
		this.dishesId = dishesId;
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}



}
