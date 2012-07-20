package com.omdasoft.orderonline.gwt.order.client.companyAdd.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after company process request.
 * 
 * @author sunny
 */
public class CompanyAddResponse implements Result {
	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public CompanyAddResponse() {

	}

}
