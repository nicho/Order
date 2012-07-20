package com.omdasoft.orderonline.gwt.order.client.companyAdd.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.presenter.CompanyAddPresenter;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.presenter.CompanyAddPresenter.CompanyAddDisplay;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.presenter.CompanyAddPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.view.CompanyAddWidget;

public class CompanyAddModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(CompanyAddPresenter.class).to(CompanyAddPresenterImpl.class);
		bind(CompanyAddDisplay.class).to(CompanyAddWidget.class);
	}

}
