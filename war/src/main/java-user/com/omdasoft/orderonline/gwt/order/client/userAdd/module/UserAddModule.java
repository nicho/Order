package com.omdasoft.orderonline.gwt.order.client.userAdd.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.userAdd.presenter.UserAddPresenter;
import com.omdasoft.orderonline.gwt.order.client.userAdd.presenter.UserAddPresenter.UserAddDisplay;
import com.omdasoft.orderonline.gwt.order.client.userAdd.presenter.UserAddPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.userAdd.view.UserAddWidget;

public class UserAddModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UserAddPresenter.class).to(UserAddPresenterImpl.class);
		bind(UserAddDisplay.class).to(UserAddWidget.class);
	}

}
