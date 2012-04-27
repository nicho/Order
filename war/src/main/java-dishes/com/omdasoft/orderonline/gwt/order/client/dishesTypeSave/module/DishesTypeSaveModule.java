package com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.presenter.DishesTypeSavePresenter;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.presenter.DishesTypeSavePresenter.DishesTypeSaveDisplay;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.presenter.DishesTypeSavePresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.view.DishesTypeSaveWidget;

public class DishesTypeSaveModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DishesTypeSavePresenter.class).to(DishesTypeSavePresenterImpl.class);
		bind(DishesTypeSaveDisplay.class).to(DishesTypeSaveWidget.class);
	}

}
