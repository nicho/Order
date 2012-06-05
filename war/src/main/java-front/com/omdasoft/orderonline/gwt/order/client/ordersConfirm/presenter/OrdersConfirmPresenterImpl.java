package com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.EltGinjector;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;


public class OrdersConfirmPresenterImpl extends
		BasePresenter<OrdersConfirmPresenter.OrdersConfirmDisplay> implements
		OrdersConfirmPresenter {

	private final DispatchAsync dispatch;

	final ErrorHandler errorHandler;
	private final EltGinjector injector = GWT.create(EltGinjector.class);
	@Inject
	public OrdersConfirmPresenterImpl(EventBus eventBus,
			OrdersConfirmDisplay display, DispatchAsync dispatch,ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler=errorHandler;

	}

	@Override
	public void bind() {
		
	}

	
	
}
