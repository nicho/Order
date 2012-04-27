/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userView.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class UserViewRequest implements Action<UserViewResponse> {

	String staffId;
	


	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}




	public UserViewRequest() {
	}
	public UserViewRequest(String staffId) {
		this.staffId=staffId;
	}


	


}
