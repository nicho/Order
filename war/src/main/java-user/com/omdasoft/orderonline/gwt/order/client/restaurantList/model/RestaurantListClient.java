package com.omdasoft.orderonline.gwt.order.client.restaurantList.model;

import java.io.Serializable;

public class RestaurantListClient implements Serializable, Comparable<RestaurantListClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;
	
	String id;
	String name;
	private String indexNo;
	private String city;
	private String address;
	private String phone;
	private String deptAdmin;
	private String did;
	
	
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getIndexNo() {
		return indexNo;
	}
	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}
	public String getDeptAdmin() {
		return deptAdmin;
	}
	public void setDeptAdmin(String deptAdmin) {
		this.deptAdmin = deptAdmin;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public RestaurantListClient() {

	}
	@Override
	public int compareTo(RestaurantListClient o) {
		// TODO Auto-generated method stub
		return 0;
	}



}
