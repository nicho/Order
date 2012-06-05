package com.omdasoft.orderonline.gwt.order.client.orderIndex.module;

import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveRequest;

/**
 * Provide LoginEvent
 * 
 * @author kmtong
 * 
 */
public class OrderManagerimpl implements OrderManager {

	OrderSaveRequest orderRequest;

	

	@Override
	public OrderSaveRequest getOrderRequest() {
		return orderRequest;
	}

	@Override
	public void setOrderRequest(OrderSaveRequest orderRequest) {
		this.orderRequest=orderRequest;
		
	}


}
