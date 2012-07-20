package com.omdasoft.orderonline.gwt.order.client.core;

import com.google.gwt.inject.client.GinModules;
import com.google.inject.name.Named;
import com.omdasoft.orderonline.gwt.order.client.core.presenter.PresentersGinjector;
import com.omdasoft.orderonline.gwt.order.client.mvp.MVPGinjector;

@GinModules( { PlatformModule.class })
public interface PlatformGinjector extends MVPGinjector, PresentersGinjector {

	Platform getPlatform();

	@Named("admin")
	PluginSet getPluginSetAdmin();

	@Named("dept")
	PluginSet getPluginSetDept();
	
	@Named("order")
	PluginSet getPluginSetOrder();
	
	@Named("platformAdmin")
	PluginSet getPluginSetPlatformAdmin();
}
