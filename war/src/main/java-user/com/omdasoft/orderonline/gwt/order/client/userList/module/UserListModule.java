package com.omdasoft.orderonline.gwt.order.client.userList.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.userList.presenter.UserListPresenter;
import com.omdasoft.orderonline.gwt.order.client.userList.presenter.UserListPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.userList.presenter.UserListPresenter.UserListDisplay;
import com.omdasoft.orderonline.gwt.order.client.userList.view.UserListWidget;

public class UserListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UserListPresenter.class).to(UserListPresenterImpl.class);
		bind(UserListDisplay.class).to(UserListWidget.class);
	}

}
