/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.restaurantSave.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class RestaurantSaveRequest implements Action<RestaurantSaveResponse> {

	
	private UserSession session;
	private String id;
	private String name;
	private String city;
	private String address;
	private String phone;
	private String deptAdminStaffId;
	private String did;

	public String getDid() {
		return did;
	}



	public void setDid(String did) {
		this.did = did;
	}



	public String getDeptAdminStaffId() {
		return deptAdminStaffId;
	}



	public void setDeptAdminStaffId(String deptAdminStaffId) {
		this.deptAdminStaffId = deptAdminStaffId;
	}



	public RestaurantSaveRequest() {
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



	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	/**
	 * 
	 * @param OrderListVo
	 */
	public RestaurantSaveRequest(UserSession session) {
		this.session=session;
	}


}
