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
public class OrderInitRequest implements Action<OrderInitResponse> {

	String corpId;
	
	

	public String getCorpId() {
		return corpId;
	}



	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}



	public OrderInitRequest() {
	}

	public OrderInitRequest(String corpId) {
		this.corpId=corpId;
	}



}
