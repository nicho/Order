/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderSave.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class FindOrderRequest implements Action<FindOrderResponse> {

	
	private String orderId;




	public String getOrderId() {
		return orderId;
	}




	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}




	public FindOrderRequest() {
	}




}
