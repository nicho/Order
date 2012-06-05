package com.omdasoft.orderonline.gwt.order.client.ordersLogin.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.presenter.OrdersLoginPresenter;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.presenter.OrdersLoginPresenter.OrdersLoginDisplay;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.presenter.OrdersLoginPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.view.OrdersLoginWidget;

public class OrdersLoginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrdersLoginPresenter.class).to(OrdersLoginPresenterImpl.class).in(Singleton.class);
		bind(OrdersLoginDisplay.class).to(OrdersLoginWidget.class).in(Singleton.class);

	}

}
