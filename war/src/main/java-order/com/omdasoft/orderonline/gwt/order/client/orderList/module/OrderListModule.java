package com.omdasoft.orderonline.gwt.order.client.orderList.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.orderList.presenter.OrderListPresenter;
import com.omdasoft.orderonline.gwt.order.client.orderList.presenter.OrderListPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.orderList.presenter.OrderListPresenter.OrderListDisplay;
import com.omdasoft.orderonline.gwt.order.client.orderList.view.OrderListWidget;

public class OrderListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderListPresenter.class).to(OrderListPresenterImpl.class);
		bind(OrderListDisplay.class).to(OrderListWidget.class);
	}

}
