package com.omdasoft.orderonline.gwt.order.client.registerHr.presenter;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.registerHr.presenter.RegisterHrPresenter.RegisterHrDisplay;
import com.omdasoft.orderonline.gwt.order.client.registerHr.view.RegisterHrWidget;

public class RegisterHrModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RegisterHrPresenter.class).to(RegisterHrPresenterImpl.class).in(Singleton.class);
		bind(RegisterHrDisplay.class).to(RegisterHrWidget.class);
	}

}
