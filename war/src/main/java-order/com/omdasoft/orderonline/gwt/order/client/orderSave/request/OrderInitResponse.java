package com.omdasoft.orderonline.gwt.order.client.orderSave.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class OrderInitResponse implements Result {

	private List<String> cityName;

	public OrderInitResponse() {

	}

	public OrderInitResponse(List<String> cityName) {
		this.cityName = cityName;

	}

	public List<String> getCityName() {
		return cityName;
	}

	public void setCityName(List<String> cityName) {
		this.cityName = cityName;
	}

}
