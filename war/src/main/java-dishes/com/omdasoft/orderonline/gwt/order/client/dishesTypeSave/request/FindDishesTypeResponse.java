package com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class FindDishesTypeResponse implements Result {
	String name;
	String rid;
	private String dishesType;

	public String getDishesType() {
		return dishesType;
	}

	public void setDishesType(String dishesType) {
		this.dishesType = dishesType;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FindDishesTypeResponse() {

	}

}
