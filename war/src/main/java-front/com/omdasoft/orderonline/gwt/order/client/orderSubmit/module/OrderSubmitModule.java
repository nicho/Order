package com.omdasoft.orderonline.gwt.order.client.orderSubmit.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.presenter.OrderSubmitPresenter;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.presenter.OrderSubmitPresenter.OrderSubmitDisplay;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.presenter.OrderSubmitPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.view.OrderSubmitWidget;

public class OrderSubmitModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderSubmitPresenter.class).to(OrderSubmitPresenterImpl.class).in(Singleton.class);
		bind(OrderSubmitDisplay.class).to(OrderSubmitWidget.class).in(Singleton.class);
	}

}
