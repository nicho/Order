package com.omdasoft.orderonline.gwt.order.client.frontOrderList.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.frontOrderList.presenter.FrontOrderListPresenter;
import com.omdasoft.orderonline.gwt.order.client.frontOrderList.presenter.FrontOrderListPresenter.FrontOrderListDisplay;
import com.omdasoft.orderonline.gwt.order.client.frontOrderList.presenter.FrontOrderListPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.frontOrderList.view.FrontOrderListWidget;

public class FrontOrderListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(FrontOrderListPresenter.class).to(FrontOrderListPresenterImpl.class).in(Singleton.class);
		bind(FrontOrderListDisplay.class).to(FrontOrderListWidget.class).in(Singleton.class);
	}

}
