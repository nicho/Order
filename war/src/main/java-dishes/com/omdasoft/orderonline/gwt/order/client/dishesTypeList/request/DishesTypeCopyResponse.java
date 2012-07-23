package com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class DishesTypeCopyResponse implements Result {

	private String fal;


	public DishesTypeCopyResponse() {

	}

	public DishesTypeCopyResponse(String fal) {

		this.fal = fal;

	}

	public String getFal() {
		return fal;
	}

	public void setFal(String fal) {
		this.fal = fal;
	}

}
