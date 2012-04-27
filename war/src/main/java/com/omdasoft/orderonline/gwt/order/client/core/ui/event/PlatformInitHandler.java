package com.omdasoft.orderonline.gwt.order.client.core.ui.event;

import com.google.gwt.event.shared.EventHandler;

public interface PlatformInitHandler extends EventHandler {

	void onInit(boolean loggedIn);

}
