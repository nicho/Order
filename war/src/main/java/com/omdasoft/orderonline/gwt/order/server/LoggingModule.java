package com.omdasoft.orderonline.gwt.order.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.omdasoft.orderonline.gwt.order.server.logger.Slf4jTypeListener;

/**
 * This Guice module handles the registration of logging annotation throughout
 * this code.
 * 
 * @author cyril
 * @since 0.0.1
 */
public class LoggingModule extends AbstractModule {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void configure() {

		logger.info("Initializing logging module...");
		configureLoggers();

		logger.info("Initializing logging module completed");

	}

	private void configureLoggers() {

		// use SLF4J logger
		bindListener(Matchers.any(), new Slf4jTypeListener());

	}

}
