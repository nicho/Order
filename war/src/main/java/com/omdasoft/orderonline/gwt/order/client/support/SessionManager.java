package com.omdasoft.orderonline.gwt.order.client.support;

import com.omdasoft.orderonline.gwt.order.client.login.event.LoginHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;



public interface SessionManager {

	public UserSession getSession();

	public void authenticate(String username, String password,String verifyCode,Display display);

	public void registerLoginEventHandler(LoginHandler handler);

	public void bind();

	public void resetLogin();
	
	public void logout();
	
	public void initialize();


}
