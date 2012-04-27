package com.omdasoft.orderonline.gwt.order.client.registerHr.request;

import net.customware.gwt.dispatch.shared.Result;

public class RegisterHrResponse implements Result {
	private String token;
	private String userId;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public RegisterHrResponse() {

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
