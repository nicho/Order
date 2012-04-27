package com.omdasoft.orderonline.gwt.order.client.register.request;

import net.customware.gwt.dispatch.shared.Result;

public class RegisterResponse implements Result {
	private String token;
	private String corpId;

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public RegisterResponse() {

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
