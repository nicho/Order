/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesSave.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class FindDishesRequest implements Action<FindDishesResponse> {

	
	private String dishesId;





	public String getDishesId() {
		return dishesId;
	}





	public void setDishesId(String dishesId) {
		this.dishesId = dishesId;
	}





	public FindDishesRequest() {
	}




}
