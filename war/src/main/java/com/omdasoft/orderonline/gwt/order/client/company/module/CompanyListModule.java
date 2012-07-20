package com.omdasoft.orderonline.gwt.order.client.company.module;

import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.company.presenter.CompanyListPresenter;
import com.omdasoft.orderonline.gwt.order.client.company.presenter.CompanyListPresenter.CompanyListDisplay;
import com.omdasoft.orderonline.gwt.order.client.company.presenter.CompanyListPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.company.view.CompanyListWidget;

public class CompanyListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(CompanyListPresenter.class).to(CompanyListPresenterImpl.class);
		bind(CompanyListDisplay.class).to(CompanyListWidget.class);
		
	}

}
