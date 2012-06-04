/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class DishesTypeSaveRequest implements Action<DishesTypeSaveResponse> {

	private UserSession session;
	private String id;
	private String name;
	private String dishesType;

	public String getDishesType() {
		return dishesType;
	}

	public void setDishesType(String dishesType) {
		this.dishesType = dishesType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DishesTypeSaveRequest() {
	}

	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	/**
	 * 
	 * @param OrderListVo
	 */
	public DishesTypeSaveRequest(UserSession session) {
		this.session = session;
	}

}
