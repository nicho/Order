/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.restaurantSave.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class FindRestaurantRequest implements Action<FindRestaurantResponse> {

	
	private String restaurantId;





	public String getRestaurantId() {
		return restaurantId;
	}





	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}





	public FindRestaurantRequest() {
	}




}
