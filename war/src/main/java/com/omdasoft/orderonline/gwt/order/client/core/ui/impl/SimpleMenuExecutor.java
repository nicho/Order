package com.omdasoft.orderonline.gwt.order.client.core.ui.impl;

import com.omdasoft.orderonline.gwt.order.client.core.ui.MenuItem;
import com.omdasoft.orderonline.gwt.order.client.core.ui.SiteManager;
import com.omdasoft.orderonline.gwt.order.client.core.ui.event.MenuClickHandler;


public class SimpleMenuExecutor implements MenuClickHandler {

	final SiteManager siteManager;

	protected SimpleMenuExecutor(SiteManager siteManager) {
		this.siteManager = siteManager;
	}

	public void onClick(MenuItem menuItem) {
		menuItem.execute();
	}

}
