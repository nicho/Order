/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderList.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria;
import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class SearchOrderListRequest implements Action<SearchOrderListResponse> {

	private OrderListCriteria criteria;
	private UserSession session;


	public SearchOrderListRequest() {
	}

	public OrderListCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(OrderListCriteria criteria) {
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
	public SearchOrderListRequest(OrderListCriteria criteria,UserSession session) {
		this.criteria = criteria;
		this.session=session;
	}


}
