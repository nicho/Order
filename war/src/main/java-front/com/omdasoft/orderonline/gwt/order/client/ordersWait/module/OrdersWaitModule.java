package com.omdasoft.orderonline.gwt.order.client.ordersWait.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.ordersWait.presenter.OrdersWaitPresenter;
import com.omdasoft.orderonline.gwt.order.client.ordersWait.presenter.OrdersWaitPresenter.OrdersWaitDisplay;
import com.omdasoft.orderonline.gwt.order.client.ordersWait.presenter.OrdersWaitPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.ordersWait.view.OrdersWaitWidget;

public class OrdersWaitModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrdersWaitPresenter.class).to(OrdersWaitPresenterImpl.class).in(Singleton.class);
		bind(OrdersWaitDisplay.class).to(OrdersWaitWidget.class).in(Singleton.class);

	}

}
