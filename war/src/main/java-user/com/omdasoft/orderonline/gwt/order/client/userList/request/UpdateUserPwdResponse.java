package com.omdasoft.orderonline.gwt.order.client.userList.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class UpdateUserPwdResponse implements Result {

	private String message;

	public UpdateUserPwdResponse() {

	}

	public UpdateUserPwdResponse(String message) {
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
