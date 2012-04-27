package com.omdasoft.orderonline.gwt.order.client.ordersDishes.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.OrdersDishesPresenter;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.OrdersDishesPresenter.OrdersDishesDisplay;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.OrdersDishesPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.view.OrdersDishesWidget;

public class OrdersDishesModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrdersDishesPresenter.class).to(OrdersDishesPresenterImpl.class).in(Singleton.class);
		bind(OrdersDishesDisplay.class).to(OrdersDishesWidget.class).in(Singleton.class);
	}

}
