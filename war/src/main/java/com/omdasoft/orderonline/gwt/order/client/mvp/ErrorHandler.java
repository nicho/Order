package com.omdasoft.orderonline.gwt.order.client.mvp;


public interface ErrorHandler {

	void alert(String message);

	void alert(String message, Throwable e);

}
