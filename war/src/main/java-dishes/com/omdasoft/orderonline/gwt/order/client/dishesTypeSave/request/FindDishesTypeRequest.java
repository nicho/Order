/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class FindDishesTypeRequest implements Action<FindDishesTypeResponse> {

	
	private String dishesTypeId;






	public String getDishesTypeId() {
		return dishesTypeId;
	}






	public void setDishesTypeId(String dishesTypeId) {
		this.dishesTypeId = dishesTypeId;
	}






	public FindDishesTypeRequest() {
	}




}
