package com.omdasoft.orderonline.gwt.order.client.ordersLogin.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;


public class OrdersLoginPresenterImpl extends
		BasePresenter<OrdersLoginPresenter.OrdersLoginDisplay> implements
		OrdersLoginPresenter {

	private final DispatchAsync dispatch;

	final ErrorHandler errorHandler;

	@Inject
	public OrdersLoginPresenterImpl(EventBus eventBus,
			OrdersLoginDisplay display, DispatchAsync dispatch,ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler=errorHandler;

	}

	@Override
	public void bind() {
		
	}

	
	
}
