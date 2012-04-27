package com.omdasoft.orderonline.gwt.order.client.userView.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.omdasoft.orderonline.gwt.order.client.userView.model.UserWinClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月15日 11:09:22
 */
public class UserWinResponse implements Result {


	private List<UserWinClient> result;
	private int total;

	
	public List<UserWinClient> getResult() {
		return result;
	}


	public void setResult(List<UserWinClient> result) {
		this.result = result;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public UserWinResponse() {

	}

	

}
