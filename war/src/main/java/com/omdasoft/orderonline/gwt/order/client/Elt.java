package com.omdasoft.orderonline.gwt.order.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Elt implements EntryPoint {

	public static final String GWT_MODULE_PATH = "/elt";
	private final EltGinjector injector = GWT.create(EltGinjector.class);

	@Override
	public void onModuleLoad() {
	//	injector.getRegisterPresenter().bind();
		
//		injector.getMain().init(RootLayoutPanel.get());
		injector.getOrderSubmitPresenter().bind();
		injector.getOrderIndexPresenter().initPresenter(injector.getOrderSubmitPresenter().getDisplay().asWidget());
		injector.getOrderIndexPresenter().bind();
		RootLayoutPanel.get().clear();
		RootLayoutPanel.get().add(injector.getOrderIndexPresenter().getDisplay().asWidget());
		
		// EventBus bus = injector.getEventBus();
		// final LoginPresenter p = injector.getLoginPresenter();
		// p.bind();
		// RootLayoutPanel.get().add(p.getDisplay().asWidget());
		//
		// bus.addHandler(LoginEvent.getType(), new LoginHandler() {
		//
		// @Override
		// public void onLogin(LoginEvent event) {
		// injector.getErrorHandler().alert(
		// "Login Event Received: " + event.getStatus());
		// if (event.getStatus() == LoginStatus.LOGIN_OK) {
		// p.unbind();
		// RootLayoutPanel.get().clear();
		// RootLayoutPanel.get().add(
		// new Label(injector.getSessionManager().getSession()
		// .getToken()));
		// }
		// }
		//
		// });

	}

}
