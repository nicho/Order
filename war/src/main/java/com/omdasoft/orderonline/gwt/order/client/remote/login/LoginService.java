package com.omdasoft.orderonline.gwt.order.client.remote.login;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.omdasoft.orderonline.gwt.order.client.support.UserSession;
import com.omdasoft.orderonline.gwt.order.model.ClientException;

@RemoteServiceRelativePath("loginService")
public interface LoginService extends RemoteService {

	public UserSession authLogin(String username, String password,String verifyCode) throws ClientException;

	
}
