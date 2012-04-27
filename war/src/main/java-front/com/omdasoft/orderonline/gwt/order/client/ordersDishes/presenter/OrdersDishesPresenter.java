package com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Panel;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveRequest;

public interface OrdersDishesPresenter extends Presenter<OrdersDishesPresenter.OrdersDishesDisplay> {
	
	public void initOrdersDishes(OrderSaveRequest request);
	public static interface OrdersDishesDisplay extends Display {

		public HasClickHandlers getAddBtnClickHandlers();
		public HasClickHandlers getReturnBtnClickHandlers();


		Panel getResultPanel();
		Panel getResultpage();
		Panel getTabpage();
		Panel getDetailPanel();
		Panel getBookingPanel();



	}
}
