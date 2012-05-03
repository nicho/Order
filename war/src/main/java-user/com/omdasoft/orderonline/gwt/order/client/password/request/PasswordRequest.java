/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.password.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

public class PasswordRequest implements Action<PasswordResponse> {

	private String userId;
	private String pwd;
	private UserSession session;
    private String oldpwd;

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public UserSession getSession() {
		return session;
	}


	public void setSession(UserSession session) {
		this.session = session;
	}


	

	public PasswordRequest() {
	}


	public PasswordRequest(String userId,String oldpwd,String pwd,UserSession session) {
		this.userId = userId;
		this.session=session;
		this.pwd=pwd;
		this.oldpwd = oldpwd;
	}


	public String getOldpwd() {
		return oldpwd;
	}


	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}


}
