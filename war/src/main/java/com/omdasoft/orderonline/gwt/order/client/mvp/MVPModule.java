package com.omdasoft.orderonline.gwt.order.client.mvp;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.mvp.impl.DefaultEventBus;
import com.omdasoft.orderonline.gwt.order.client.mvp.impl.WindowAlertErrorHandler;

public class MVPModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bind(ErrorHandler.class).to(WindowAlertErrorHandler.class).in(
				Singleton.class);
	}
}