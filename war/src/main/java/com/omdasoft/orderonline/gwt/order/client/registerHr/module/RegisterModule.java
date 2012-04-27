package com.omdasoft.orderonline.gwt.order.client.registerHr.module;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.register.presenter.RegisterPresenter;
import com.omdasoft.orderonline.gwt.order.client.register.presenter.RegisterPresenter.RegisterDisplay;
import com.omdasoft.orderonline.gwt.order.client.register.presenter.RegisterPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.register.view.RegisterWidget;

public class RegisterModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RegisterPresenter.class).to(RegisterPresenterImpl.class).in(Singleton.class);
		bind(RegisterDisplay.class).to(RegisterWidget.class);
	}

}
