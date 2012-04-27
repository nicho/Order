package com.omdasoft.orderonline.gwt.order.client.restaurantSave.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.presenter.RestaurantSavePresenter;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.presenter.RestaurantSavePresenter.RestaurantSaveDisplay;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.presenter.RestaurantSavePresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.view.RestaurantSaveWidget;

public class RestaurantSaveModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RestaurantSavePresenter.class).to(RestaurantSavePresenterImpl.class);
		bind(RestaurantSaveDisplay.class).to(RestaurantSaveWidget.class);
	}

}
