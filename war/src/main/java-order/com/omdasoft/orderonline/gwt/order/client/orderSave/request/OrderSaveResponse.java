package com.omdasoft.orderonline.gwt.order.client.orderSave.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class OrderSaveResponse implements Result {

	private int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public OrderSaveResponse() {

	}

	public OrderSaveResponse(int total) {
		this.total = total;

	}

}
