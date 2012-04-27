package com.omdasoft.orderonline.gwt.order.client.dishesSave.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class DishesSaveResponse implements Result {

	private int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public DishesSaveResponse() {

	}

	public DishesSaveResponse(int total) {
		this.total = total;

	}

}
