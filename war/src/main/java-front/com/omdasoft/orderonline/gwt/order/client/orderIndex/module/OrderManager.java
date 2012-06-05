package com.omdasoft.orderonline.gwt.order.client.orderIndex.module;

import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveRequest;




public interface OrderManager {

	public OrderSaveRequest getOrderRequest();
	public void setOrderRequest(OrderSaveRequest orderRequest);



}
