package com.omdasoft.orderonline.gwt.order.client.userList.model;

import java.io.Serializable;

public class UserListClient implements Serializable, Comparable<UserListClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;
	
	String staffNo;
	String staffName;
	String departmentName;
	String jobPosition;
	String phone;
	String staffId;
	String photo;
    String email;
    String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getJobPosition() {
		return jobPosition;
	}
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public UserListClient() {

	}
	@Override
	public int compareTo(UserListClient o) {
		// TODO Auto-generated method stub
		return 0;
	}



}
