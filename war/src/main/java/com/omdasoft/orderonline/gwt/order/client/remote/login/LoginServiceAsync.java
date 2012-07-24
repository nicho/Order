package com.omdasoft.orderonline.gwt.order.client.remote.login;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.omdasoft.orderonline.gwt.order.client.support.UserSession;


public interface LoginServiceAsync {

	void authLogin(String username, String password, String verifyCode,
			AsyncCallback<UserSession> callback);

}
