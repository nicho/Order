package com.omdasoft.orderonline.gwt.order.client.orderIndex.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.presenter.OrderIndexPresenter;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.presenter.OrderIndexPresenter.OrderIndexDisplay;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.presenter.OrderIndexPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.view.OrderIndexWidget;

public class OrderIndexModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderIndexPresenter.class).to(OrderIndexPresenterImpl.class).in(Singleton.class);
		bind(OrderIndexDisplay.class).to(OrderIndexWidget.class).in(Singleton.class);
		
		
		bind(OrderManager.class).to(OrderManagerimpl.class).in(Singleton.class);
		
	}

}
