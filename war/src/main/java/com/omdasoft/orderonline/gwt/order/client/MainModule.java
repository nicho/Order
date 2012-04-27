package com.omdasoft.orderonline.gwt.order.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.support.impl.CookieSessionManager;

/**
 * Define the main initializer
 * 
 * @author kmtong
 * 
 */
public class MainModule extends AbstractGinModule {

	protected void configure() {

		// bind(DispatchAsync.class).to(GinStandardDispatchAsync.class);
		// bind(ExceptionHandler.class).to(DefaultExceptionHandler.class);

		bind(Main.class).to(MainImpl.class);
		bind(SessionManager.class).to(CookieSessionManager.class).in(Singleton.class);
	}

}
