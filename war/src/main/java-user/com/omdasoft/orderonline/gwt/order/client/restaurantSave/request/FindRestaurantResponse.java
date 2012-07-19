package com.omdasoft.orderonline.gwt.order.client.restaurantSave.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class FindRestaurantResponse implements Result {
	private String name;
	private String city;
	private String address;
	private String phone;
	private String deptAdminStaffId;
	private String deptAdminStaffName;
	private String did;

	public String getDeptAdminStaffName() {
		return deptAdminStaffName;
	}



	public String getDid() {
		return did;
	}



	public void setDid(String did) {
		this.did = did;
	}



	public void setDeptAdminStaffName(String deptAdminStaffName) {
		this.deptAdminStaffName = deptAdminStaffName;
	}



	public String getDeptAdminStaffId() {
		return deptAdminStaffId;
	}



	public void setDeptAdminStaffId(String deptAdminStaffId) {
		this.deptAdminStaffId = deptAdminStaffId;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public FindRestaurantResponse() {

	}

	

}
