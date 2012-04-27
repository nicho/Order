package com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model;

import java.io.Serializable;

public class DishesTypeListClient implements Serializable, Comparable<DishesTypeListClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;
	
	String id;
	String rid;
	String name;

	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
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
	public DishesTypeListClient() {

	}
	@Override
	public int compareTo(DishesTypeListClient o) {
		// TODO Auto-generated method stub
		return 0;
	}



}
