package com.omdasoft.orderonline.gwt.order.client.ordersWait.request;

import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria.OrderStatus;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class OrderWaitResponse implements Result {
	
	private  OrderStatus status;
	
	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public OrderWaitResponse(OrderStatus status) {
		this.status=status;
	}
	public OrderWaitResponse() {

	}

}
