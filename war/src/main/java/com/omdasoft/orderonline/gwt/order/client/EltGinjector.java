package com.omdasoft.orderonline.gwt.order.client;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.inject.client.GinModules;
import com.omdasoft.orderonline.gwt.order.client.core.PlatformGinjector;
import com.omdasoft.orderonline.gwt.order.client.dispatch.EltStandardDispatchModule;
import com.omdasoft.orderonline.gwt.order.client.frontOrderList.presenter.FrontOrderListPresenter;
import com.omdasoft.orderonline.gwt.order.client.login.presenter.LoginModule;
import com.omdasoft.orderonline.gwt.order.client.login.presenter.LoginPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.module.OrderManager;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.presenter.OrderIndexPresenter;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.presenter.OrderSubmitPresenter;
import com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter.OrdersConfirmPresenter;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.OrdersDishesPresenter;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.presenter.OrdersLoginPresenter;
import com.omdasoft.orderonline.gwt.order.client.ordersWait.presenter.OrdersWaitPresenter;
import com.omdasoft.orderonline.gwt.order.client.register.presenter.RegisterPresenter;
import com.omdasoft.orderonline.gwt.order.client.registerHr.presenter.RegisterHrPresenter;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;

@GinModules({ MainModule.class, LoginModule.class, EltStandardDispatchModule.class })
public interface EltGinjector extends PlatformGinjector {

	// MainPresenter getMainPresenter();

	LoginPresenter getLoginPresenter();
	RegisterPresenter getRegisterPresenter();
	RegisterHrPresenter getRegisterHrPresenter();
	OrderIndexPresenter getOrderIndexPresenter();
	OrderSubmitPresenter getOrderSubmitPresenter();
	OrdersDishesPresenter getOrdersDishesPresenter();
	FrontOrderListPresenter getFrontOrderListPresenter();
	EventBus getEventBus();
	DispatchAsync getDispatch();
	SessionManager getSessionManager();

	ErrorHandler getErrorHandler();
	OrderManager getOrderManager();
	Main getMain();
	
	OrdersLoginPresenter getOrderLoginPresenter();
	OrdersConfirmPresenter getOrdersConfirmPresenter();
	OrdersWaitPresenter getOrdersWaitPresenter();
}
