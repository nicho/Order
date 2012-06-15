package com.omdasoft.orderonline.gwt.order.client.dishesList.model;

import java.io.Serializable;

public class DishesListClient implements Serializable, Comparable<DishesListClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;
	
	String id;
	String rid;
	String name;
	private String dishesType;	
	private String description;	
	private String photo;	
	private String price;
	private String indexNo;
	private String taste;
	
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public String getIndexNo() {
		return indexNo;
	}
	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getDishesType() {
		return dishesType;
	}
	public void setDishesType(String dishesType) {
		this.dishesType = dishesType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	public DishesListClient() {

	}
	@Override
	public int compareTo(DishesListClient o) {
		// TODO Auto-generated method stub
		return 0;
	}



}
