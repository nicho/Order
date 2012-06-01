package com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveRequest;

public interface OrdersDishesPresenter extends Presenter<OrdersDishesPresenter.OrdersDishesDisplay> {
	public void updateDishesList(String id,String name,String price);
	public void initOrdersDishes(OrderSaveRequest request);
	public void refulDishes(String typeId);
	public static interface OrdersDishesDisplay extends Display {

		public HasClickHandlers getAddBtnClickHandlers();
		public HasClickHandlers getReturnBtnClickHandlers();


		Panel getResultPanel();
		Panel getResultpage();
		Panel getTabpage();
		//Panel getDetailPanel();
		Panel getBookingPanel();
		InlineLabel getMenutypeAll();	
		void setCity(String text);
		void setRestaurant(String text);
		void setOrderUser(String text);
		void setNumber(String text);
		void setRoom(String text);
		void setDishesNumber(String text);
		void setDishesMoney(String text);
		void setBookingTitle(String text);
		void hiddenDishesNumber(boolean fal);
	//	Anchor getTypeall();
		public ListBox getPageNumber();
	}
}
