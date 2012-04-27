package com.omdasoft.orderonline.gwt.order.client.orderView.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.orderView.presenter.OrderViewPresenter;
import com.omdasoft.orderonline.gwt.order.client.orderView.presenter.OrderViewPresenter.OrderViewDisplay;
import com.omdasoft.orderonline.gwt.order.client.orderView.presenter.OrderViewPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.orderView.view.OrderViewWidget;

public class OrderViewModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderViewPresenter.class).to(OrderViewPresenterImpl.class);
		bind(OrderViewDisplay.class).to(OrderViewWidget.class);
	}

}
