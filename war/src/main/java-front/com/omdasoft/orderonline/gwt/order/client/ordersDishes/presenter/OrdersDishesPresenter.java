package com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter;


import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesClient;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface OrdersDishesPresenter extends Presenter<OrdersDishesPresenter.OrdersDishesDisplay> {
	public void updateDishesList(String id,String name,String price,List<String> tasteList);

	public void initDishesList(List<BookingDishesClient> dishesList);
	public void refulDishes(String typeId);
	public void initDeptId(String deptId);	
	public void cleanDishes();
	public static interface OrdersDishesDisplay extends Display {

		public HasClickHandlers getAddBtnClickHandlers();
		public HasClickHandlers getReturnBtnClickHandlers();


		Panel getResultPanel();
		Panel getResultpage();
		Panel getTabpage();
		//Panel getDetailPanel();
		Panel getBookingPanel();
		InlineLabel getMenutypeAll();	
		Image getOrdernow();	
		void setCity(String text);
		void setRestaurant(String text);
		void setOrderUser(String text);
		void setNumber(String text);
		void setRoom(String text);
		void setDishesNumber(String text);
		void setDishesMoney(String text);
		void setBookingTitle(String text);
		void setphone(String text);
		void hiddenDishesNumber(boolean fal);
	//	Anchor getTypeall();
		public ListBox getPageNumber();
		
		TextBox getQueryKey();
		
		Anchor getQuerybtn();
	}
}
