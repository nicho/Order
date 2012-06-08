package com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class DishesTypePanelResponse implements Result {

	private List<String> typeNameList;

	
	public List<String> getTypeNameList() {
		return typeNameList;
	}

	public void setTypeNameList(List<String> typeNameList) {
		this.typeNameList = typeNameList;
	}

	public DishesTypePanelResponse() {

	}

	public DishesTypePanelResponse(List<String> typeNameList) {
		this.typeNameList = typeNameList;

	}

}
