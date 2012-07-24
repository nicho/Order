package com.omdasoft.orderonline.gwt.order.client.win;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.win.alert.AlertDialog;
import com.omdasoft.orderonline.gwt.order.client.win.alert.AlertPresenter;
import com.omdasoft.orderonline.gwt.order.client.win.alert.AlertPresenter.AlertDisplay;
import com.omdasoft.orderonline.gwt.order.client.win.alert.AlertPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.win.alert.AlertWidget;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.ConfirmDialog;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.ConfirmPresenter;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.ConfirmPresenter.ConfirmDisplay;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.ConfirmPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.ConfirmWidget;
import com.omdasoft.orderonline.gwt.order.client.win.loginconfirm.LoginConfirmDialog;
import com.omdasoft.orderonline.gwt.order.client.win.loginconfirm.LoginConfirmPresenter;
import com.omdasoft.orderonline.gwt.order.client.win.loginconfirm.LoginConfirmPresenter.LoginConfirmDisplay;
import com.omdasoft.orderonline.gwt.order.client.win.loginconfirm.LoginConfirmPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.win.loginconfirm.LoginConfirmWidget;

public class WinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(Win.class);

		bind(ConfirmPresenter.class).to(ConfirmPresenterImpl.class);
		bind(ConfirmDisplay.class).to(ConfirmWidget.class);
		bind(ConfirmDialog.class);

		bind(AlertPresenter.class).to(AlertPresenterImpl.class);
		bind(AlertDisplay.class).to(AlertWidget.class);
		bind(AlertDialog.class);
		
		bind(LoginConfirmPresenter.class).to(LoginConfirmPresenterImpl.class);
		bind(LoginConfirmDisplay.class).to(LoginConfirmWidget.class);
		bind(LoginConfirmDialog.class);

	}

}
