package com.omdasoft.orderonline.gwt.order.client.mvp.impl;

import com.google.gwt.user.client.Window;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;

@Singleton
public class WindowAlertErrorHandler implements ErrorHandler {

	public void alert(String message) {
		Window.alert(message);
	}

	public void alert(String message, Throwable e) {
		// custom alert message
		Window.alert(message + "\n\n" + e.toString());
	}
}
