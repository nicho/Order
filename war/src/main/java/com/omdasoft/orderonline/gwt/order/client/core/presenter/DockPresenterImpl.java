package com.omdasoft.orderonline.gwt.order.client.core.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.EltGinjector;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.core.PluginManager;
import com.omdasoft.orderonline.gwt.order.client.core.presenter.DockPresenter.DockDisplay;
import com.omdasoft.orderonline.gwt.order.client.core.ui.DialogCloseListener;
import com.omdasoft.orderonline.gwt.order.client.core.ui.MenuProcessor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.event.MenuClickEvent;
import com.omdasoft.orderonline.gwt.order.client.dishesList.plugin.DishesListConstants;
import com.omdasoft.orderonline.gwt.order.client.login.LastLoginRoleRequest;
import com.omdasoft.orderonline.gwt.order.client.login.LastLoginRoleResponse;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginEvent;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.orderList.plugin.OrderListConstants;
import com.omdasoft.orderonline.gwt.order.client.password.dialog.PasswordDialog;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.userList.plugin.UserListConstants;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;


public class DockPresenterImpl extends BasePresenter<DockDisplay> implements
		DockPresenter {
	final PluginManager pluginManager;
	final SessionManager sessionManager;
	final EltGinjector injector;
	final MenuProcessor menuProcessor;
	final DispatchAsync dispatchAsync;
	private final Provider<PasswordDialog> passwordDialogProvider;
	@Inject
	public DockPresenterImpl(EventBus eventBus, DockDisplay display,
			SessionManager sessionManager, PluginManager pluginManager,
			EltGinjector injector, MenuProcessor menuProcessor,
			DispatchAsync dispatchAsync,Provider<PasswordDialog> passwordDialogProvider) {
		super(eventBus, display);
		this.sessionManager = sessionManager;
		this.pluginManager = pluginManager;
		this.injector = injector;
		this.menuProcessor = menuProcessor;
		this.dispatchAsync = dispatchAsync;
		this.passwordDialogProvider=passwordDialogProvider;
	}
	 String btnstyleOn = display.getManagementCenter().getElement().getParentElement().getClassName();
	 String btnstyleNo = display.getDeptManagement().getElement().getParentElement().getClassName();
		boolean fal1=false;
		boolean fal2=false;
	public void bind() {

		UserRoleVo[] role=sessionManager.getSession().getUserRoles();
		if(role!=null && role.length>0)
		{
			for (int i = 0; i < role.length; i++) {
				UserRoleVo re=role[i];
				if(re==UserRoleVo.CORP_ADMIN)
				{
					fal1=true;
				}
				if(re==UserRoleVo.DEPT_MGR)
				{
					fal2=true;
				}
			}
		}

		
		if(sessionManager.getSession().getLastLoginRole()!=UserRoleVo.CORP_ADMIN)
		{
			display.disableManagementCenter();
			display.getManagementCenter().getElement().getParentElement().setClassName(btnstyleNo);
			display.getDeptManagement().getElement().getParentElement().setClassName(btnstyleOn);
			display.setMessageTop("分店:"+sessionManager.getSession().getDepartmentName());
		}
		else
		{
			display.setMessageTop("总店:"+sessionManager.getSession().getCorporationName());
		}

		registerHandler(display.getbtnPassward().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final PasswordDialog dialog = passwordDialogProvider.get();
	
				Platform.getInstance().getSiteManager()
						.openDialog(dialog, new DialogCloseListener() {
							public void onClose(String dialogId,String instanceId) {
								 
							}
						});
			}
		}));
		registerHandler(display.getlogBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
			}
		}));
		registerHandler(display.getbtnUser().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("用户管理");
						menuProcessor.initrender(display.getMenu(),
								"User");
						eventBus.fireEvent(new MenuClickEvent(
								menuProcessor
										.getMenuItem(UserListConstants.MENU_USERLIST_SEARCH)));
					}
				}));
		registerHandler(display.getbtnOrder().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("订单处理");
						menuProcessor.initrender(display.getMenu(),
								"Order");
						eventBus.fireEvent(new MenuClickEvent(
								menuProcessor
										.getMenuItem(OrderListConstants.MENU_ORDERLIST_SEARCH)));
					}
				}));
		registerHandler(display.getbtnDishesMenu().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("菜品管理");
						menuProcessor.initrender(display.getMenu(),
								"Menu");
						eventBus.fireEvent(new MenuClickEvent(
								menuProcessor
										.getMenuItem(DishesListConstants.MENU_DISHESLIST_SEARCH)));
					}
				}));
		
		

		

		if(fal1==true && fal2==true)
		{
			registerHandler(display.getDeptManagement().addClickHandler(
					new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							 
							display.getManagementCenter().getElement().getParentElement().setClassName(btnstyleNo);
							display.getDeptManagement().getElement().getParentElement().setClassName(btnstyleOn);
							
							dispatchAsync.execute(new LastLoginRoleRequest(
									sessionManager.getSession().getToken(),
									UserRoleVo.DEPT_MGR),
									new AsyncCallback<LastLoginRoleResponse>() {
	
										@Override
										public void onFailure(Throwable e) {
											// Window.alert("系统切换出错");
										}
	
										@Override
										public void onSuccess(
												LastLoginRoleResponse resp) {
											// 成功
											if ("success".equals(resp.getFal()))
												Window.Location.reload();
											else
												Window.alert("系统切换出错");
	
										}
									});
							
						}
					}));
	
		
	
		registerHandler(display.getCorpManagement().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.getManagementCenter().getElement().getParentElement().setClassName(btnstyleOn);
						display.getDeptManagement().getElement().getParentElement().setClassName(btnstyleNo);
						
						dispatchAsync.execute(new LastLoginRoleRequest(
								sessionManager.getSession().getToken(),
								UserRoleVo.CORP_ADMIN),
								new AsyncCallback<LastLoginRoleResponse>() {

									@Override
									public void onFailure(Throwable e) {
										// Window.alert("系统切换出错");
									}

									@Override
									public void onSuccess(
											LastLoginRoleResponse resp) {
										// 成功
										if ("success".equals(resp.getFal()))
											Window.Location.reload();
										else
											Window.alert("系统切换出错");

									}
								});
						
					}
				}));
		}
		else
		{
			if(fal1==false)
			display.displayCorp();
			if(fal2==false)
			display.displayDept();
		}
	}

	public DockDisplay getDisplay() {
		return display;
	}

}
