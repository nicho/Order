package com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter;


import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface OrdersConfirmPresenter extends Presenter<OrdersConfirmPresenter.OrdersConfirmDisplay> {

	public static interface OrdersConfirmDisplay extends Display {
		Panel getBookingPanel();
		void setDishesNumber(String text);
		void setDishesMoney(String text);
		Image getNextBtn();
	}
}
