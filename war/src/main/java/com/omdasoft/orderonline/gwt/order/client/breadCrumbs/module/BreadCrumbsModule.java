package com.omdasoft.orderonline.gwt.order.client.breadCrumbs.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter.BreadCrumbsDisplay;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.ui.BreadCrumbsMenu;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.ui.impl.BreadCrumbsMenuImpl;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.view.BreadCrumbsWidget;

public class BreadCrumbsModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BreadCrumbsPresenter.class).to(BreadCrumbsPresenterImpl.class).in(Singleton.class);
		bind(BreadCrumbsDisplay.class).to(BreadCrumbsWidget.class);
		
		
		
		bind(BreadCrumbsMenu.class).to(BreadCrumbsMenuImpl.class).in(Singleton.class);
		
	}

}
