/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesList.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.dishesList.model.DishesListCriteria;
import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class SearchDishesListRequest implements Action<SearchDishesListResponse> {

	private DishesListCriteria criteria;
	private UserSession session;


	public SearchDishesListRequest() {
	}

	public DishesListCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(DishesListCriteria criteria) {
		this.criteria = criteria;
	}

	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	/**
	 * 
	 * @param DishesListVo
	 */
	public SearchDishesListRequest(DishesListCriteria criteria,UserSession session) {
		this.criteria = criteria;
		this.session=session;
	}


}
