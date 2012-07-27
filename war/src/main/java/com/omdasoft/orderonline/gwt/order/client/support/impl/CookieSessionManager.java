package com.omdasoft.orderonline.gwt.order.client.support.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.event.PlatformInitEvent;
import com.omdasoft.orderonline.gwt.order.client.login.LastLoginRoleRequest;
import com.omdasoft.orderonline.gwt.order.client.login.LastLoginRoleResponse;
import com.omdasoft.orderonline.gwt.order.client.login.TokenValidRequest;
import com.omdasoft.orderonline.gwt.order.client.login.TokenValidResponse;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginEvent;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginHandler;
import com.omdasoft.orderonline.gwt.order.client.login.presenter.LoginPresenter.LoginDisplay;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.remote.login.LoginServiceAsync;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.support.UserSession;
import com.omdasoft.orderonline.gwt.order.client.ui.DialogBox;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.WinEvent;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.WinHandler;
import com.omdasoft.orderonline.gwt.order.client.win.loginconfirm.LoginConfirmDialog;
import com.omdasoft.orderonline.gwt.order.model.ClientException;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

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
	private final LoginServiceAsync loginService;
	final Provider<LoginConfirmDialog> logindialogProvider;
	private static final int COOKIE_TIMEOUT = 1000 * 60 * 60;
	List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();

	@Inject
	public CookieSessionManager(UserSession session, final EventBus eventBus,
			DispatchAsync dispatchAsync, LoginServiceAsync loginService,
			Provider<LoginConfirmDialog> logindialogProvider) {
		this.session = session;
		this.eventBus = eventBus;
		this.dispatchAsync = dispatchAsync;
		this.loginService = loginService;
		this.logindialogProvider = logindialogProvider;
	}

	public void authenticate(String username, String password,
			String verifyCode, Display display) {
		if (display instanceof LoginDisplay) {
			if (null == username || username.trim().equals("")) {
				((LoginDisplay) display).setMessage("账号不能为空!");
				return;
			}
			if (null == password || password.trim().equals("")) {
				((LoginDisplay) display).setMessage("密码不能为空!");
				return;
			}

			logininit(username, password, verifyCode, (LoginDisplay) display);
		}

	}

	// ---------------------------------------------
	/**
	 * (原始登陆,判断所有)
	 * 
	 * @param username
	 * @param password
	 * @param verifyCode
	 * @param display
	 */
	private void logininit(String username, String password, String verifyCode,
			final LoginDisplay display) {
		loginService.authLogin(username, password, verifyCode,
				new AsyncCallback<UserSession>() {

					@Override
					public void onSuccess(final UserSession resp) {
						tokenObtained(resp);

						UserRoleVo role = resp.getLastLoginRole();
						// 判断最后一次登录的权限是否被删除
						List<UserRoleVo> roleslt = new ArrayList<UserRoleVo>();
						UserRoleVo[] roles = resp.getUserRoles();
						if (roles.length > 0) {
							for (UserRoleVo r : roles) {
								roleslt.add(r);
							}
							if (roleslt.contains(UserRoleVo.CORP_ADMIN)
									&& roleslt.contains(UserRoleVo.DEPT_MGR)) {
								final DialogBox dialogBox = new DialogBox();
								final LoginConfirmDialog dialog = logindialogProvider
										.get();
								dialog.setTitle("权限选择");
								final HandlerRegistration registration = eventBus
										.addHandler(WinEvent.getType(),
												new WinHandler() {
													@Override
													public void confirm() {

														dialogBox.hide();

														if (UserRoleVo.valueOf(dialog
																.getLoginType()) == UserRoleVo.CORP_ADMIN) {
															session.setLastLoginRole(UserRoleVo.CORP_ADMIN);

														

															eventBus.fireEvent(new LoginEvent(
																	LoginEvent.LoginStatus.LOGIN_OK));

														} else if (UserRoleVo.valueOf(dialog
																.getLoginType()) == UserRoleVo.DEPT_MGR) {
															session.setLastLoginRole(UserRoleVo.DEPT_MGR);

			

															eventBus.fireEvent(new LoginEvent(
																	LoginEvent.LoginStatus.LOGIN_OK_DEPT));
														}

														updateLastLoginRole(
																resp.getToken(),
																session.getLastLoginRole());

													}
												});

								ScrollPanel panel = new ScrollPanel();
								panel.add(dialog.asWidget());
								dialogBox.setWidget(panel);
								dialogBox.setGlassEnabled(true);
								dialogBox.setAnimationEnabled(true);
								dialogBox.setText(dialog.getTitle());
								dialogBox.setDialog(dialog);
								dialogBox.center();
								dialogBox.setPopupPosition(
										Window.getClientWidth() / 4,
										Window.getClientHeight() / 4);
								dialogBox.show();
								dialogBox
										.addCloseHandler(new CloseHandler<PopupPanel>() {

											@Override
											public void onClose(
													CloseEvent<PopupPanel> event) {
												registration.removeHandler();
												dialogBox.hide();
											}
										});
							} else {
								if (role != null && roleslt.contains(role)) {
									if (role == UserRoleVo.CORP_ADMIN)
										eventBus.fireEvent(new LoginEvent(
												LoginEvent.LoginStatus.LOGIN_OK));
									else if (role == UserRoleVo.DEPT_MGR)
										eventBus.fireEvent(new LoginEvent(
												LoginEvent.LoginStatus.LOGIN_OK_DEPT));
									else if (role == UserRoleVo.PLATFORM_ADMIN)
										eventBus.fireEvent(new LoginEvent(
												LoginEvent.LoginStatus.LOGIN_OK_PLATFORM_ADMIN));

								} else {
									if (roleslt.contains(UserRoleVo.CORP_ADMIN)) {
										role = UserRoleVo.CORP_ADMIN;
										session.setLastLoginRole(role);
										eventBus.fireEvent(new LoginEvent(
												LoginEvent.LoginStatus.LOGIN_OK));
									} else if (roleslt
											.contains(UserRoleVo.DEPT_MGR)) {
										role = UserRoleVo.DEPT_MGR;
										session.setLastLoginRole(role);
										eventBus.fireEvent(new LoginEvent(
												LoginEvent.LoginStatus.LOGIN_OK_DEPT));
									} else if (roleslt
											.contains(UserRoleVo.PLATFORM_ADMIN)) {
										role = UserRoleVo.PLATFORM_ADMIN;
										session.setLastLoginRole(role);
										eventBus.fireEvent(new LoginEvent(
												LoginEvent.LoginStatus.LOGIN_OK_PLATFORM_ADMIN));
									} else
										Window.alert("没有角色");

								}
								if (role != null) {

									updateLastLoginRole(resp.getToken(), role);

								}
							}

						}
					}

					@Override
					public void onFailure(Throwable e) {
						tokenObtained(null);

						display.setMessage(e.getMessage());

						eventBus.fireEvent(new LoginEvent(
								LoginEvent.LoginStatus.LOGIN_FAILED, e));

					}
				});

	}

	public void logout() {
		this.session.setToken(null);
		eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
	}

	public void relogout() {
		this.session.setToken(null);
		eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.RELOGOUT));
	}

	public UserSession getSession() {
		String token = Cookies.getCookie("token");
		if (!StringUtil.isEmpty(token))
			return session;
		else {

			relogout();
			throw new ClientException("登录失效,请重新登录!");
		}
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
			eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
		} else {
			eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
		}
	}

	protected void tokenObtained(UserSession rep) {
		if (rep != null && rep.getToken() != null) {
			session.setToken(rep.getToken());
			session.setLoginName(rep.getLoginName());
			session.setCorporationId(rep.getCorporationId());
			session.setUserRoles(rep.getUserRoles());
			session.setDepartmentId(rep.getDepartmentId());
			session.setDepartmentName(rep.getDepartmentName());
			session.setStaffId(rep.getStaffId());
			session.setStaffName(rep.getStaffName());
			session.setLastLoginRole(rep.getLastLoginRole());
			session.setCorporationName(rep.getCorporationName());
			session.setPhoto(rep.getPhoto());
			session.setCid(rep.getCid());
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
			session.setDepartmentId(rep.getDepartmentId());
			session.setDepartmentName(rep.getDepartmentName());
			session.setStaffId(rep.getStaffId());
			session.setStaffName(rep.getStaffName());
			session.setLastLoginRole(rep.getLastLoginRole());
			session.setCorporationName(rep.getCorporationName());
			session.setPhoto(rep.getPhoto());
			session.setCid(rep.getCid());
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
						public void onSuccess(final TokenValidResponse resp) {
							tokenObtainedToo(resp);

							UserRoleVo role = resp.getLastLoginRole();
							// 判断最后一次登录的权限是否被删除
							List<UserRoleVo> roleslt = new ArrayList<UserRoleVo>();
							UserRoleVo[] roles = resp.getUserRoles();
							if (roles.length > 0) {
								for (UserRoleVo r : roles) {
									roleslt.add(r);
								}

								if (role != null && roleslt.contains(role)) {
									if (role == UserRoleVo.CORP_ADMIN) {

										eventBus.fireEvent(new LoginEvent(
												LoginEvent.LoginStatus.LOGIN_OK));
									} else if (role == UserRoleVo.DEPT_MGR) {
								
										eventBus.fireEvent(new LoginEvent(
												LoginEvent.LoginStatus.LOGIN_OK_DEPT));

									} else if (role == UserRoleVo.PLATFORM_ADMIN)
										eventBus.fireEvent(new LoginEvent(
												LoginEvent.LoginStatus.LOGIN_OK_PLATFORM_ADMIN));

								} else {
									eventBus.fireEvent(new PlatformInitEvent(
											false));
								}

							}
						}
					});

		} else {
			eventBus.fireEvent(new PlatformInitEvent(false));
		}

	}

	private void updateLastLoginRole(String userId, UserRoleVo userRoleVo) {
		dispatchAsync.execute(new LastLoginRoleRequest(userId, userRoleVo),
				new AsyncCallback<LastLoginRoleResponse>() {

					@Override
					public void onFailure(Throwable e) {
						tokenObtained(null);
						eventBus.fireEvent(new PlatformInitEvent(false));
					}

					@Override
					public void onSuccess(LastLoginRoleResponse resp) {
						// 成功
						if ("success".equals(resp.getFal()))
							GWT.log("success update last login role ");

					}
				});
	}

}
