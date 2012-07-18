package com.omdasoft.orderonline.gwt.order.client.ordersLogin.presenter;


import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface OrdersLoginPresenter extends Presenter<OrdersLoginPresenter.OrdersLoginDisplay> {

	public void rsDoLogin();
	public static interface OrdersLoginDisplay extends Display {
		TextBox getPhone();
		Image getSubmitBtn();
		ListBox getCity();
		
		ListBox getrestaurant();
	}
}
