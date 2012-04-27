package com.omdasoft.orderonline.gwt.order.client.dictionarySave.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class FindDictionaryResponse implements Result {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FindDictionaryResponse() {

	}

	

}
