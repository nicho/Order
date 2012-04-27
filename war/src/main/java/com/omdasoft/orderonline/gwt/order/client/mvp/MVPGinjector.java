package com.omdasoft.orderonline.gwt.order.client.mvp;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({ MVPModule.class })
public interface MVPGinjector extends Ginjector {

	EventBus getEventBus();

	ErrorHandler getErrorHandler();

}
