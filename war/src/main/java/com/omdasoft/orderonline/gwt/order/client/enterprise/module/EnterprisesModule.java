package com.omdasoft.orderonline.gwt.order.client.enterprise.module;

import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.enterprise.presenter.EnterprisePresenter;
import com.omdasoft.orderonline.gwt.order.client.enterprise.presenter.EnterprisePresenter.EnterpriseDisplay;
import com.omdasoft.orderonline.gwt.order.client.enterprise.presenter.EnterprisePresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.enterprise.view.EnterpriseWidget;

public class EnterprisesModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EnterprisePresenter.class).to(EnterprisePresenterImpl.class);
		bind(EnterpriseDisplay.class).to(EnterpriseWidget.class);
		

	}

}
