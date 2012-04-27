package com.omdasoft.orderonline.gwt.order.client.dictionarySave.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.presenter.DictionarySavePresenter;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.presenter.DictionarySavePresenter.DictionarySaveDisplay;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.presenter.DictionarySavePresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.view.DictionarySaveWidget;

public class DictionarySaveModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DictionarySavePresenter.class).to(DictionarySavePresenterImpl.class);
		bind(DictionarySaveDisplay.class).to(DictionarySaveWidget.class);
	}

}
