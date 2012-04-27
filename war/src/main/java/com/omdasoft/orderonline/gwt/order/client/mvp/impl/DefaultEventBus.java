package com.omdasoft.orderonline.gwt.order.client.mvp.impl;

import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;

@Singleton
public class DefaultEventBus extends HandlerManager implements EventBus {

	@Inject
	public DefaultEventBus() {
		super(null);
	}

}
