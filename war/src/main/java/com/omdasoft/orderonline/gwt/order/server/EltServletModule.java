package com.omdasoft.orderonline.gwt.order.server;

import net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.omdasoft.orderonline.guice.EltModule;
import com.omdasoft.orderonline.gwt.order.client.Elt;
import com.omdasoft.orderonline.gwt.order.server.login.LoginServiceImpl;
import com.omdasoft.orderonline.module.RestfulModule;

/**
 * 
 * 
 */
public class EltServletModule extends ServletModule {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void configureServlets() {
		logger.info("Configuring servlet modules...");
		serve(Elt.GWT_MODULE_PATH + "/dispatch").with(
				GuiceStandardDispatchServlet.class);
		bind(InitServlet.class).in(Singleton.class);
		serve(Elt.GWT_MODULE_PATH + "/donottouchme").with(InitServlet.class);
		
		bind(LoginServiceImpl.class).in(Singleton.class);
		serve(Elt.GWT_MODULE_PATH + "/loginService").with(LoginServiceImpl.class);
		
		
		install(new EltModule());
		install(new RestfulModule());

		logger.info("Configuring servlet modules completed.");

	}

}
