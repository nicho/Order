package com.omdasoft.orderonline.gwt.order.client;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.awardShopLattice.module.AwardShopLatticeModule;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.module.BreadCrumbsModule;
import com.omdasoft.orderonline.gwt.order.client.company.module.CompanyListModule;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.module.CompanyAddModule;
import com.omdasoft.orderonline.gwt.order.client.core.presenter.DockModule;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.module.DictionaryListModule;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.module.DictionarySaveModule;
import com.omdasoft.orderonline.gwt.order.client.dishesList.module.DishesListModule;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.module.DishesSaveModule;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.module.DishesTypeListModule;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.module.DishesTypeSaveModule;
import com.omdasoft.orderonline.gwt.order.client.enterprise.module.EnterprisesModule;
import com.omdasoft.orderonline.gwt.order.client.frontOrderList.module.FrontOrderListModule;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.module.OrderIndexModule;
import com.omdasoft.orderonline.gwt.order.client.orderList.module.OrderListModule;
import com.omdasoft.orderonline.gwt.order.client.orderSave.module.OrderSaveModule;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.module.OrderSubmitModule;
import com.omdasoft.orderonline.gwt.order.client.orderView.module.OrderViewModule;
import com.omdasoft.orderonline.gwt.order.client.ordersConfirm.module.OrdersConfirmModule;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.module.OrdersDishesModule;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.module.OrdersLoginModule;
import com.omdasoft.orderonline.gwt.order.client.ordersWait.module.OrdersWaitModule;
import com.omdasoft.orderonline.gwt.order.client.password.module.PasswordModule;
import com.omdasoft.orderonline.gwt.order.client.registerHr.module.RegisterModule;
import com.omdasoft.orderonline.gwt.order.client.registerHr.presenter.RegisterHrModule;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.module.RestaurantListModule;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.module.RestaurantSaveModule;
import com.omdasoft.orderonline.gwt.order.client.userAdd.module.UserAddModule;
import com.omdasoft.orderonline.gwt.order.client.userList.module.UserListModule;
import com.omdasoft.orderonline.gwt.order.client.userView.module.UserViewModule;
import com.omdasoft.orderonline.gwt.order.client.win.WinModule;

public class PresenterModule extends AbstractGinModule {

	@Override
	protected void configure() {
		install(new DockModule());

		install(new WinModule());
		install(new BreadCrumbsModule());
		install(new UserListModule());
		install(new RestaurantListModule());
		install(new DictionaryListModule());
		install(new DishesListModule());
		install(new DishesTypeListModule());
		install(new OrderListModule());
		install(new OrderSaveModule());
		install(new DictionarySaveModule());
		install(new RestaurantSaveModule());
		install(new DishesSaveModule());
		install(new DishesTypeSaveModule());
		install(new OrdersDishesModule());
		install(new EnterprisesModule());
		install(new RegisterModule());
		install(new RegisterHrModule());
		install(new OrderSubmitModule());
		install(new OrderIndexModule());
		install(new FrontOrderListModule());
		install(new OrderViewModule());
		install(new UserAddModule());
		install(new UserViewModule());
		install(new PasswordModule());
		install(new AwardShopLatticeModule());
		install(new OrdersLoginModule());
		install(new OrdersConfirmModule());
		install(new OrdersWaitModule());
		install(new CompanyListModule());
		install(new CompanyAddModule());
		

	}

}
