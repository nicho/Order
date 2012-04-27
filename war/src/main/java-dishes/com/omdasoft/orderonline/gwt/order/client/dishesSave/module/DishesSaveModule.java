package com.omdasoft.orderonline.gwt.order.client.dishesSave.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.presenter.DishesSavePresenter;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.presenter.DishesSavePresenter.DishesSaveDisplay;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.presenter.DishesSavePresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.view.DishesSaveWidget;

public class DishesSaveModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DishesSavePresenter.class).to(DishesSavePresenterImpl.class);
		bind(DishesSaveDisplay.class).to(DishesSaveWidget.class);
	}

}
