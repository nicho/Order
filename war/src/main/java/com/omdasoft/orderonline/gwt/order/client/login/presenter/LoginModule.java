package com.omdasoft.orderonline.gwt.order.client.login.presenter;

import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.login.presenter.LoginPresenter.LoginDisplay;

public class LoginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(LoginPresenter.class).to(LoginPresenterImpl.class);
		bind(LoginDisplay.class).to(LoginWidget.class);
	}

}
