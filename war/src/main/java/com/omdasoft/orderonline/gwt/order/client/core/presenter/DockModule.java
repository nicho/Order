package com.omdasoft.orderonline.gwt.order.client.core.presenter;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.core.presenter.DockPresenter.DockDisplay;
import com.omdasoft.orderonline.gwt.order.client.core.presenter.PlatformPresenter.PlatformDisplay;
import com.omdasoft.orderonline.gwt.order.client.core.view.DockWidget;
import com.omdasoft.orderonline.gwt.order.client.core.view.PlatformWidget;

public class DockModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DockPresenter.class).to(DockPresenterImpl.class).in(Singleton.class);
		bind(DockDisplay.class).to(DockWidget.class);
		
		bind(PlatformPresenter.class).to(PlatformPresenterImpl.class).in(Singleton.class);
		bind(PlatformDisplay.class).to(PlatformWidget.class);
	}

}
