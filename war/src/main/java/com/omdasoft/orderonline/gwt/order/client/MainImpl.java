package com.omdasoft.orderonline.gwt.order.client;

import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.event.PlatformInitEvent;
import com.omdasoft.orderonline.gwt.order.client.core.ui.event.PlatformInitHandler;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginEvent;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginHandler;
import com.omdasoft.orderonline.gwt.order.client.login.presenter.LoginPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class MainImpl implements Main, PlatformInitHandler, LoginHandler {

	final EltGinjector injector;
	final SessionManager sessionManager;
	final EventBus eventBus;
	final Win win;
	RootLayoutPanel rootLayoutPanel;
	LoginPresenter login;
	final DispatchAsync dispatchAsync;

	@Inject
	public MainImpl(EltGinjector injector, SessionManager sessionManager,
			EventBus eventBus, Win win, DispatchAsync dispatchAsync) {
		this.injector = injector;
		this.sessionManager = sessionManager;
		this.eventBus = eventBus;
		this.win = win;
		login = injector.getLoginPresenter();
		this.dispatchAsync = dispatchAsync;
	}

	public void init(RootLayoutPanel panel) {
		GWT.log("Main Initializing...");
		this.rootLayoutPanel = panel;
		eventBus.addHandler(PlatformInitEvent.getType(), this);
		eventBus.addHandler(LoginEvent.getType(), this);
		sessionManager.initialize();
//		dispatchAsync.execute(new RegisterInitRequest(),
//				new AsyncCallback<RegisterInitResponse>() {
//					public void onFailure(Throwable caught) {
//
//						Window.alert("初始化失败");
//					}
//
//					@Override
//					public void onSuccess(RegisterInitResponse response) {
//						OrgInitVo vo = response.getOrgInitVo();
//						if (vo == null || vo.getCorpInit() == 0) {// 初始化企业
//							RootLayoutPanel.get().clear();
//							injector.getRegisterPresenter().bind();
//							RootLayoutPanel.get().add(
//									injector.getRegisterPresenter()
//											.getDisplay().asWidget());
//						} else if (vo != null && vo.getCorpInit() != 0
//								&& vo.getHrInit() == 0) {// 初始化HR账户
//							RootLayoutPanel.get().clear();
//							injector.getRegisterHrPresenter().bind();
//							RootLayoutPanel.get().add(
//									injector.getRegisterHrPresenter()
//											.getDisplay().asWidget());
//						} else {
//						
//						}
//
//					}
//
//				});

	}

	public void onInit(boolean loggedIn) {
		rootLayoutPanel.clear();
		if (!loggedIn) {
			Map<String, List<String>> maps = Window.Location.getParameterMap();
			String loginType = "";
			if (maps.get("loginType") != null)
				loginType = maps.get("loginType").get(0) + "";
			if (!StringUtil.isEmpty(loginType)) {
				login.bind();
				rootLayoutPanel.add(login.getDisplay().asWidget());
			} else {
				login.bind();
				rootLayoutPanel.add(login.getDisplay().asWidget());
			}

		} else {
			login.unbind();
			UserRoleVo role = sessionManager.getSession().getLastLoginRole();
			if (role == UserRoleVo.CORP_ADMIN)
				injector.getPlatform().initialize(injector.getPluginSetAdmin(),
						rootLayoutPanel);
			else if (role == UserRoleVo.DEPT_MGR)
				injector.getPlatform().initialize(injector.getPluginSetDept(),
						rootLayoutPanel);
			else if (role == UserRoleVo.PLATFORM_ADMIN)
				injector.getPlatform().initializePlatform(
						injector.getPluginSetPlatformAdmin(), rootLayoutPanel);
		}
	}

	public void onLogin(LoginEvent event) {
		switch (event.getStatus()) {
		case LOGIN_OK:
			rootLayoutPanel.clear();
			login.unbind();
			injector.getPlatform().initialize(injector.getPluginSetAdmin(),
					rootLayoutPanel);
			break;
		case LOGIN_OK_DEPT:
			rootLayoutPanel.clear();
			login.unbind();
			injector.getPlatform().initialize(injector.getPluginSetDept(),
					rootLayoutPanel);
			break;
		case LOGIN_OK_PLATFORM_ADMIN:
			rootLayoutPanel.clear();
			login.unbind();
			injector.getPlatform().initializePlatform(
					injector.getPluginSetPlatformAdmin(), rootLayoutPanel);
			break;
		case LOGIN_FAILED:
			// win.alert("登录失败，请重试！");
			break;
		case LOGIN_EXPIRED:
		case LOGOUT:
			// if (!GWT.isScript()) {
			// break;
			// }
			// win.alert("Logout event received");
			// sessionManager.logout();
			sessionManager.resetLogin();
			Window.Location.reload();
			// rootLayoutPanel.clear();
			// login.bind();
			// rootLayoutPanel.add(login.getDisplay().asWidget());
			break;
		case RELOGOUT:
			// if (!GWT.isScript()) {
			// break;
			// }
			// win.alert("Logout event received");
			// sessionManager.logout();
			Window.alert("登录失效,请重新登录！");
			sessionManager.resetLogin();
			Window.Location.reload();
			// rootLayoutPanel.clear();
			// login.bind();
			// rootLayoutPanel.add(login.getDisplay().asWidget());
			break;

		}
	}

}
