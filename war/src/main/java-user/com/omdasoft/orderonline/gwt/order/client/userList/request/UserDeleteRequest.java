/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userList.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class UserDeleteRequest implements Action<UserDeleteResponse> {

	private String id;
	private UserSession session;
	private int statusfal;



	public int getStatusfal() {
		return statusfal;
	}


	public void setStatusfal(int statusfal) {
		this.statusfal = statusfal;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public UserDeleteRequest() {
	}

	
	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	/**
	 * 
	 * @param DishesListVo
	 */
	public UserDeleteRequest(String id,UserSession session,int statusfal) {
		this.id=id;
		this.session=session;
		this.statusfal=statusfal;
	}


}
