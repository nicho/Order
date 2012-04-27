/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderView.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.orderView.model.OrderDishesCriteria;
import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class SearchOrderDishesListRequest implements Action<SearchOrderDishesListResponse> {

	private OrderDishesCriteria criteria;
	private UserSession session;


	public SearchOrderDishesListRequest() {
	}

	public OrderDishesCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(OrderDishesCriteria criteria) {
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
	 * @param OrderListVo
	 */
	public SearchOrderDishesListRequest(OrderDishesCriteria criteria,UserSession session) {
		this.criteria = criteria;
		this.session=session;
	}


}
