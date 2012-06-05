package com.omdasoft.orderonline.gwt.order.client.ordersConfirm.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter.OrdersConfirmPresenter;
import com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter.OrdersConfirmPresenter.OrdersConfirmDisplay;
import com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter.OrdersConfirmPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.ordersConfirm.view.OrdersConfirmWidget;

public class OrdersConfirmModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrdersConfirmPresenter.class).to(OrdersConfirmPresenterImpl.class).in(Singleton.class);
		bind(OrdersConfirmDisplay.class).to(OrdersConfirmWidget.class).in(Singleton.class);

	}

}
