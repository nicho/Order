/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesList.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class DishesCopyRequest implements Action<DishesCopyResponse> {

	private String userId;

	public DishesCopyRequest() {
	}

	/**
	 * 
	 * @param DishesListVo
	 */
	public DishesCopyRequest(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
