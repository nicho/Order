package com.omdasoft.orderonline.gwt.order.client.orderSave.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.orderSave.presenter.OrderSavePresenter;
import com.omdasoft.orderonline.gwt.order.client.orderSave.presenter.OrderSavePresenter.OrderSaveDisplay;
import com.omdasoft.orderonline.gwt.order.client.orderSave.presenter.OrderSavePresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.orderSave.view.OrderSaveWidget;

public class OrderSaveModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderSavePresenter.class).to(OrderSavePresenterImpl.class);
		bind(OrderSaveDisplay.class).to(OrderSaveWidget.class);
	}

}
