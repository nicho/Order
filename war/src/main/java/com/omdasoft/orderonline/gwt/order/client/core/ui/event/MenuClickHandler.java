package com.omdasoft.orderonline.gwt.order.client.core.ui.event;

import com.google.gwt.event.shared.EventHandler;
import com.omdasoft.orderonline.gwt.order.client.core.ui.MenuItem;

public interface MenuClickHandler extends EventHandler {

	void onClick(MenuItem menuItem);

}
