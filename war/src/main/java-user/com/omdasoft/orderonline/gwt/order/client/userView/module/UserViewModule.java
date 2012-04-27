package com.omdasoft.orderonline.gwt.order.client.userView.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.userView.presenter.UserViewPresenter;
import com.omdasoft.orderonline.gwt.order.client.userView.presenter.UserViewPresenter.UserViewDisplay;
import com.omdasoft.orderonline.gwt.order.client.userView.presenter.UserViewPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.userView.view.UserViewWidget;

public class UserViewModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UserViewPresenter.class).to(UserViewPresenterImpl.class);
		bind(UserViewDisplay.class).to(UserViewWidget.class);
	}

}
