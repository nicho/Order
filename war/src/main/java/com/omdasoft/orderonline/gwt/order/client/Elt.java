package com.omdasoft.orderonline.gwt.order.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.omdasoft.orderonline.gwt.order.client.core.request.ImageUrlInitRequest;
import com.omdasoft.orderonline.gwt.order.client.core.request.ImageUrlInitResponse;

public class Elt implements EntryPoint {

	public static final String GWT_MODULE_PATH = "/elt";
	public static  String GWT_IMAGE_PATH = "upload/";
	private final EltGinjector injector = GWT.create(EltGinjector.class);
	public static String CORPORATIONID="";
	public static String DEPARTMENTID="";
	public static String ADMINPAGE="";
	@Override
	public void onModuleLoad() {
		Map<String, List<String>> maps = Window.Location.getParameterMap(); 
		if(maps.get("cid")!=null)
		    CORPORATIONID=maps.get("cid").get(0)+"";
		if(maps.get("did")!=null)
			DEPARTMENTID=maps.get("did").get(0)+"";
		if(maps.get("page")!=null)
			ADMINPAGE=maps.get("page").get(0)+"";
		
		
		
		injector.getDispatch().execute(new ImageUrlInitRequest(CORPORATIONID,DEPARTMENTID),
				new AsyncCallback<ImageUrlInitResponse>() {
					@Override
					public void onFailure(Throwable e) {
						injector.getErrorHandler().alert(e.getMessage());
					}

					@Override
					public void onSuccess(
							ImageUrlInitResponse response) {
							CORPORATIONID=response.getCorpId();
							DEPARTMENTID=response.getDeptId();
							GWT_IMAGE_PATH=response.getUrl();
							RootLayoutPanel.get().clear();
							
							if("ADMIN".equals(ADMINPAGE))
								injector.getMain().init(RootLayoutPanel.get());
							else
							{
								injector.getOrderIndexPresenter().initPresenter(injector.getOrdersDishesPresenter());
								injector.getOrderIndexPresenter().bind();
								RootLayoutPanel.get().add(injector.getOrderIndexPresenter().getDisplay().asWidget());
							}
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
