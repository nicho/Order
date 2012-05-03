package com.omdasoft.orderonline.gwt.order.client.password.module;

import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.password.presenter.PasswordPresenter;
import com.omdasoft.orderonline.gwt.order.client.password.presenter.PasswordPresenter.PasswordDisplay;
import com.omdasoft.orderonline.gwt.order.client.password.presenter.PasswordPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.password.view.PasswordWidget;

public class PasswordModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(PasswordPresenter.class).to(PasswordPresenterImpl.class);
		bind(PasswordDisplay.class).to(PasswordWidget.class);
	}

}
