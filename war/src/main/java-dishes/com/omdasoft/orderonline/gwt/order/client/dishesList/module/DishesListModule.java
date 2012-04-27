package com.omdasoft.orderonline.gwt.order.client.dishesList.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.dishesList.presenter.DishesListPresenter;
import com.omdasoft.orderonline.gwt.order.client.dishesList.presenter.DishesListPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.dishesList.presenter.DishesListPresenter.DishesListDisplay;
import com.omdasoft.orderonline.gwt.order.client.dishesList.view.DishesListWidget;

public class DishesListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DishesListPresenter.class).to(DishesListPresenterImpl.class);
		bind(DishesListDisplay.class).to(DishesListWidget.class);
	}

}
