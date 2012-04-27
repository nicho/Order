package com.omdasoft.orderonline.gwt.order.client.dictionaryList.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.presenter.DictionaryListPresenter;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.presenter.DictionaryListPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.presenter.DictionaryListPresenter.DictionaryListDisplay;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.view.DictionaryListWidget;

public class DictionaryListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DictionaryListPresenter.class).to(DictionaryListPresenterImpl.class);
		bind(DictionaryListDisplay.class).to(DictionaryListWidget.class);
	}

}
