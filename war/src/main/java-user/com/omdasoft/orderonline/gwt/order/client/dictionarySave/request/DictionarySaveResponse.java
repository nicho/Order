package com.omdasoft.orderonline.gwt.order.client.dictionarySave.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class DictionarySaveResponse implements Result {

	private int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public DictionarySaveResponse() {

	}

	public DictionarySaveResponse(int total) {
		this.total = total;

	}

}
