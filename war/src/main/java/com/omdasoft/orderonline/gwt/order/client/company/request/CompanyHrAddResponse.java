package com.omdasoft.orderonline.gwt.order.client.company.request;

import net.customware.gwt.dispatch.shared.Result;

public class CompanyHrAddResponse implements Result{
	private String message;

	public CompanyHrAddResponse() {

	}

	public CompanyHrAddResponse(String message) {
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
