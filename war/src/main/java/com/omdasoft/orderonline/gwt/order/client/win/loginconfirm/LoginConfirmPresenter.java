package com.omdasoft.orderonline.gwt.order.client.win.loginconfirm;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.omdasoft.orderonline.gwt.order.client.mvp.DialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;

public interface LoginConfirmPresenter extends DialogPresenter<LoginConfirmPresenter.LoginConfirmDisplay> {

	public static interface LoginConfirmDisplay extends Display {
		HasClickHandlers getOkBtn();
		String getLoginType();

	}

}
