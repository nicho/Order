package com.omdasoft.orderonline.gwt.order.client.core.presenter;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.core.presenter.DockPresenter.DockDisplay;
import com.omdasoft.orderonline.gwt.order.client.core.view.DockWidget;

public class DockModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DockPresenter.class).to(DockPresenterImpl.class).in(Singleton.class);
		bind(DockDisplay.class).to(DockWidget.class);
		

	}

}
