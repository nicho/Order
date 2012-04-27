package com.omdasoft.orderonline.gwt.order.client.dictionaryList.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class DictionaryDeleteResponse implements Result {

	private int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public DictionaryDeleteResponse() {

	}

	public DictionaryDeleteResponse(int total) {

		this.total = total;

	}

}
