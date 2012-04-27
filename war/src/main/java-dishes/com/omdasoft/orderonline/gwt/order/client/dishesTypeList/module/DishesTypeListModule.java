package com.omdasoft.orderonline.gwt.order.client.dishesTypeList.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.presenter.DishesTypeListPresenter;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.presenter.DishesTypeListPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.presenter.DishesTypeListPresenter.DishesTypeListDisplay;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.view.DishesTypeListWidget;

public class DishesTypeListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DishesTypeListPresenter.class).to(DishesTypeListPresenterImpl.class);
		bind(DishesTypeListDisplay.class).to(DishesTypeListWidget.class);
	}

}
