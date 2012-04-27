package com.omdasoft.orderonline.gwt.order.client.support.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.event.PlatformInitEvent;
import com.omdasoft.orderonline.gwt.order.client.login.LoginRequest;
import com.omdasoft.orderonline.gwt.order.client.login.LoginResponse;
import com.omdasoft.orderonline.gwt.order.client.login.TokenValidRequest;
import com.omdasoft.orderonline.gwt.order.client.login.TokenValidResponse;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginEvent;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginHandler;
import com.omdasoft.orderonline.gwt.order.client.login.presenter.AlertErrorWidget;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.support.UserSession;
import com.omdasoft.orderonline.gwt.order.client.ui.DialogBox;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;

/**
 * Provide LoginEvent
 * 
 * @author kmtong
 * 
 */
public class CookieSessionManager implements SessionManager {

	final UserSession session;
	final EventBus eventBus;
	final DispatchAsync dispatchAsync;

	private static final int COOKIE_TIMEOUT = 1000 * 3600 * 24 * 7;
	List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();

	@Inject
	public CookieSessionManager(UserSession session, final EventBus eventBus,
			DispatchAsync dispatchAsync) {
		this.session = session;
		this.eventBus = eventBus;
		this.dispatchAsync = dispatchAsync;


	}

	public void authenticate(String username, String password, String verifyCode) {
		if (null == username || username.trim().equals("")) {
			//Window.alert("账号不能为空!");
			final AlertErrorWidget ae = new AlertErrorWidget();
			final DialogBox dialogBoxae = new DialogBox();
			ae.getOkBtn().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent arg0) {
					dialogBoxae.hide();
				}
			});
			ae.setMessage("账号不能为空!");
			dialogBoxae.setWidget(ae);
			dialogBoxae.setGlassEnabled(true);
			dialogBoxae.setAnimationEnabled(true);
			dialogBoxae.setWidth("350px");
			dialogBoxae.setText("提示");
			dialogBoxae.center();
			dialogBoxae.show();
			return;
		}
		if (null == password || password.trim().equals("")) {
			//Window.alert("密码不能为空!");
			final AlertErrorWidget ae = new AlertErrorWidget();
			final DialogBox dialogBoxae = new DialogBox();
			ae.getOkBtn().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent arg0) {
					dialogBoxae.hide();
				}
			});
			ae.setMessage("密码不能为空!");
			dialogBoxae.setWidget(ae);
			dialogBoxae.setGlassEnabled(true);
			dialogBoxae.setAnimationEnabled(true);
			dialogBoxae.setWidth("350px");
			dialogBoxae.setText("提示");
			dialogBoxae.center();
			dialogBoxae.show();
			return;
		}

		LoginRequest req = new LoginRequest(username, password, verifyCode);
		dispatchAsync.execute(req, new AsyncCallback<LoginResponse>() {

			@Override
			public void onFailure(Throwable e) {
				tokenObtained(null);
			
				final AlertErrorWidget ae = new AlertErrorWidget();
				final DialogBox dialogBoxae = new DialogBox();
				ae.getOkBtn().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						dialogBoxae.hide();
					}
				});
				ae.setMessage(e.getMessage());
				dialogBoxae.setWidget(ae);
				dialogBoxae.setGlassEnabled(true);
				dialogBoxae.setAnimationEnabled(true);
				dialogBoxae.setWidth("350px");
				dialogBoxae.setText("提示");
				dialogBoxae.center();
				dialogBoxae.show();
				eventBus.fireEvent(new LoginEvent(
						LoginEvent.LoginStatus.LOGIN_FAILED, e));
			}

			@Override
			public void onSuccess(LoginResponse resp) {
				tokenObtained(resp);

				UserRoleVo role = resp.getLastLoginRole();
				if(role!=null)
				{
					if (role == UserRoleVo.CORP_ADMIN)
						 eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGIN_OK));
					else if (role == UserRoleVo.DEPT_MGR)
						 eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGIN_OK_DEPT));

				}
				else
				{
				List <UserRoleVo> roleslt = new ArrayList<UserRoleVo>();
				UserRoleVo [] roles=resp.getUserRoles();
				
					if(roles.length>0)
					{
						for (UserRoleVo r:roles) {
							roleslt.add(r);
						}
						
						if(roleslt.size()>0)
						{
							if(roleslt.contains(UserRoleVo.CORP_ADMIN))
							{
								 role=UserRoleVo.CORP_ADMIN;
								 eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGIN_OK));
							}
							else if(roleslt.contains(UserRoleVo.DEPT_MGR))
							{
								 role=UserRoleVo.DEPT_MGR;
								 eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGIN_OK_DEPT));
							}
							else 
							{
								final AlertErrorWidget ae = new AlertErrorWidget();
								final DialogBox dialogBoxae = new DialogBox();
								ae.getOkBtn().addClickHandler(new ClickHandler() {
									@Override
									public void onClick(ClickEvent arg0) {
										dialogBoxae.hide();
									}
								});
								ae.setMessage("没有权限登录系统!");
								dialogBoxae.setWidget(ae);
								dialogBoxae.setGlassEnabled(true);
								dialogBoxae.setAnimationEnabled(true);
								dialogBoxae.setWidth("350px");
								dialogBoxae.setText("提示");
								dialogBoxae.center();
								dialogBoxae.show();
							}
	
						}
					}
				}


			}
		});
	}

	public void logout() {
		this.session.setToken(null);
		eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
	}

	public UserSession getSession() {
		return session;
	}

	public void registerLoginEventHandler(LoginHandler handler) {
		registerHandler(eventBus.addHandler(LoginEvent.getType(), handler));
	}

	protected void registerHandler(HandlerRegistration handlerRegistration) {
		handlerRegistrations.add(handlerRegistration);
	}

	public void bind() {
		GWT.log("SessionManager Bind");
		String token = Cookies.getCookie("token");
		if (token != null) {
			// userService.reauthenticate(token, new AsyncCallback<String>() {
			// public void onFailure(Throwable e) {
			// Window.alert("Login Error: " + e.getMessage());
			// tokenObtained(null);
			// // no login token in cookie
			// eventBus.fireEvent(new LoginEvent(
			// LoginEvent.LoginStatus.LOGIN_EXPIRED));
			// }
			//
			// public void onSuccess(String token) {
			// tokenObtained(token);
			// eventBus.fireEvent(new LoginEvent(
			// LoginEvent.LoginStatus.LOGIN_OK));
			// }
			// });
			eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
		} else {
			eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
		}
	}

	protected void tokenObtained(LoginResponse rep) {
		if (rep != null && rep.getToken() != null) {
			session.setToken(rep.getToken());
			session.setLoginName(rep.getLoginName());
			session.setCorporationId(rep.getCorporationId());
			session.setUserRoles(rep.getUserRoles());
			session.setCorporationName(rep.getCorporationName());
			session.setDepartmentId(rep.getDepartmentId());
			session.setRestaurantId(rep.getDepartmentId());
			session.setPhoto(rep.getPhoto());
			Date expires = new Date((new Date()).getTime() + COOKIE_TIMEOUT);
			Cookies.setCookie("token", rep.getToken(), expires);

		} else {
			session.setToken(null);
			Cookies.removeCookie("token");
		}
	}

	protected void tokenObtainedToo(TokenValidResponse rep) {
		if (rep != null && rep.getToken() != null) {
			session.setToken(rep.getToken());
			session.setLoginName(rep.getLoginName());
			session.setCorporationId(rep.getCorporationId());
			session.setUserRoles(rep.getUserRoles());
			session.setCorporationName(rep.getCorporationName());
			session.setPhoto(rep.getPhoto());
			session.setDepartmentId(rep.getDepartmentId());
			session.setRestaurantId(rep.getDepartmentId());
			Date expires = new Date((new Date()).getTime() + COOKIE_TIMEOUT);
			Cookies.setCookie("token", rep.getToken(), expires);

		} else {
			session.setToken(null);
			Cookies.removeCookie("token");
		}
	}

	public void resetLogin() {
		tokenObtained(null);
	}

	@Override
	public void initialize() {
		// Check Cookie Validity
		GWT.log("Initializing SessionManager...");
		String token = Cookies.getCookie("token");

		System.out.println("token==========" + token);
		if (null != token && !token.trim().equals("")) {
			// check the token value.
			dispatchAsync.execute(new TokenValidRequest(token),
					new AsyncCallback<TokenValidResponse>() {

						@Override
						public void onFailure(Throwable e) {
							tokenObtained(null);
							eventBus.fireEvent(new PlatformInitEvent(false));
						}

						@Override
						public void onSuccess(TokenValidResponse resp) {
							tokenObtainedToo(resp);
							List <UserRoleVo> roleslt = new ArrayList<UserRoleVo>();
							UserRoleVo [] roles=resp.getUserRoles();
							
								if(roles.length>0)
								{
									for (UserRoleVo r:roles) {
										roleslt.add(r);
									}
									
									if(roleslt.size()>0)
									{
										if(roleslt.contains(UserRoleVo.CORP_ADMIN))
										{

											 eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGIN_OK));
										}
										else if(roleslt.contains(UserRoleVo.DEPT_MGR))
										{
											
											 eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGIN_OK_DEPT));
										}
										else 
										{
											final AlertErrorWidget ae = new AlertErrorWidget();
											final DialogBox dialogBoxae = new DialogBox();
											ae.getOkBtn().addClickHandler(new ClickHandler() {
												@Override
												public void onClick(ClickEvent arg0) {
													dialogBoxae.hide();
												}
											});
											ae.setMessage("没有权限登录系统!");
											dialogBoxae.setWidget(ae);
											dialogBoxae.setGlassEnabled(true);
											dialogBoxae.setAnimationEnabled(true);
											dialogBoxae.setWidth("350px");
											dialogBoxae.setText("提示");
											dialogBoxae.center();
											dialogBoxae.show();
											eventBus.fireEvent(new PlatformInitEvent(false));
										}
									}
								}
						}
					});

		} else {
			eventBus.fireEvent(new PlatformInitEvent(false));
		}

	}

}
