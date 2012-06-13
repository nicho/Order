package com.omdasoft.orderonline.gwt.order.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.omdasoft.orderonline.gwt.order.client.core.request.ImageUrlInitRequest;
import com.omdasoft.orderonline.gwt.order.client.core.request.ImageUrlInitResponse;

public class Elt implements EntryPoint {

	public static final String GWT_MODULE_PATH = "/elt";
	public static  String GWT_IMAGE_PATH = "upload/";
	private final EltGinjector injector = GWT.create(EltGinjector.class);

	@Override
	public void onModuleLoad() {
	
		injector.getDispatch().execute(new ImageUrlInitRequest(),
				new AsyncCallback<ImageUrlInitResponse>() {
					@Override
					public void onFailure(Throwable e) {
						injector.getErrorHandler().alert(e.getMessage());
					}

					@Override
					public void onSuccess(
							ImageUrlInitResponse response) {
						
							GWT_IMAGE_PATH=response.getUrl();
							RootLayoutPanel.get().clear();
							injector.getOrderIndexPresenter().initPresenter(injector.getOrdersDishesPresenter());
							injector.getOrderIndexPresenter().bind();
							RootLayoutPanel.get().add(injector.getOrderIndexPresenter().getDisplay().asWidget());
						}
				
				});
		
		
		
	//	injector.getRegisterPresenter().bind();
		
	//	injector.getMain().initOrder(RootLayoutPanel.get());

		
		
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
