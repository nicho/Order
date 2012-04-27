package com.omdasoft.orderonline.gwt.order.client.restaurantList.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.ChooseLeaderWinPresenter;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.ChooseLeaderWinPresenter.ChooseLeaderWinDisplay;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.ChooseLeaderWinPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.ChooseLeaderWinWidget;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.presenter.RestaurantListPresenter;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.presenter.RestaurantListPresenter.RestaurantListDisplay;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.presenter.RestaurantListPresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.view.RestaurantListWidget;

public class RestaurantListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RestaurantListPresenter.class).to(RestaurantListPresenterImpl.class);
		bind(RestaurantListDisplay.class).to(RestaurantListWidget.class);
		
		bind(ChooseLeaderWinPresenter.class).to(ChooseLeaderWinPresenterImpl.class);
		bind(ChooseLeaderWinDisplay.class).to(ChooseLeaderWinWidget.class);
	}

}
