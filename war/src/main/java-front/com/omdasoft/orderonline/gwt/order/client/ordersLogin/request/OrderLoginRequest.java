/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.ordersLogin.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class OrderLoginRequest implements Action<OrderLoginResponse> {

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public OrderLoginRequest(String phone) {
		this.phone=phone;
	}
	public OrderLoginRequest() {

	}
}
