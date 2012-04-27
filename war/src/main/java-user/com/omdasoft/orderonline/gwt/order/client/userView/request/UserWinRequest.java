/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userView.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.userView.model.UserWinCriteria;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月15日 11:09:28
 */
public class UserWinRequest implements Action<UserWinResponse> {

	private UserWinCriteria criteria;

	public UserWinCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(UserWinCriteria criteria) {
		this.criteria = criteria;
	}

	public UserWinRequest() {
	}

	public UserWinRequest(UserWinCriteria criteria) {
		this.criteria = criteria;
	}

}
