package com.omdasoft.orderonline.gwt.order.client;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.event.PlatformInitEvent;
import com.omdasoft.orderonline.gwt.order.client.core.ui.event.PlatformInitHandler;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginEvent;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginHandler;
import com.omdasoft.orderonline.gwt.order.client.login.presenter.AlertErrorWidget;
import com.omdasoft.orderonline.gwt.order.client.login.presenter.LoginPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.register.model.OrgInitVo;
import com.omdasoft.orderonline.gwt.order.client.register.request.RegisterInitRequest;
import com.omdasoft.orderonline.gwt.order.client.register.request.RegisterInitResponse;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.ui.DialogBox;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;

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

		dispatchAsync.execute(new RegisterInitRequest(),
				new AsyncCallback<RegisterInitResponse>() {
					public void onFailure(Throwable caught) {

						Window.alert("初始化失败");
					}

					@Override
					public void onSuccess(RegisterInitResponse response) {
						OrgInitVo vo = response.getOrgInitVo();
						if (vo == null || vo.getCorpInit()==0) {// 初始化企业
							injector.getRegisterPresenter().bind();
							RootLayoutPanel.get().add(injector.getRegisterPresenter().getDisplay().asWidget());
						} else if (vo != null && vo.getCorpInit() != 0	&& vo.getHrInit() == 0) {// 初始化HR账户
							injector.getRegisterHrPresenter().bind();
							RootLayoutPanel.get().add(injector.getRegisterHrPresenter().getDisplay().asWidget());
						}
						else {
							sessionManager.initialize();
						}

					}

				});

	}

	public void onInit(boolean loggedIn) {
		rootLayoutPanel.clear();
		if (!loggedIn) {
			login.bind();
			rootLayoutPanel.add(login.getDisplay().asWidget());
		} else {
			login.unbind();
			
			List <UserRoleVo> roleslt = new ArrayList<UserRoleVo>();
			UserRoleVo [] roles=sessionManager.getSession().getUserRoles();
			
				if(roles.length>0)
				{
					for (UserRoleVo r:roles) {
						roleslt.add(r);
					}
					
					if(roleslt.size()>0)
					{
						if(roleslt.contains(UserRoleVo.PLATFORM_ADMIN))
						{

							 eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGIN_OK_PLATFORM_ADMIN));
						}
						else if(roleslt.contains(UserRoleVo.CORP_ADMIN))
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
							 eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGIN_FAILED));
						}
					}
				}

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
			injector.getPlatform().initializePlatform(injector.getPluginSetPlatformAdmin(),
					rootLayoutPanel);
			break;
		case LOGIN_FAILED:
			win.alert("登录失败，请重试！");
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

		}
	}

	@Override
	public void initOrder(RootLayoutPanel panel) {
		panel.clear();
		injector.getPlatform().initializeOrder(injector.getPluginSetOrder(),
				panel);
	}

}
