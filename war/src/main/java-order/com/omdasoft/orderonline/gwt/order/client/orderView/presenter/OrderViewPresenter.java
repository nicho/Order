package com.omdasoft.orderonline.gwt.order.client.orderView.presenter;


import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface OrderViewPresenter extends Presenter<OrderViewPresenter.OrderViewDisplay> {
	
public void initOrder(String orderId);
	public static interface OrderViewDisplay extends Display {
		
		InlineLabel getCity();
		
		InlineLabel getrestaurant();
		
		InlineLabel getReserveTime();
		
		InlineLabel getAmountOfClient();
		
		InlineLabel getFavoriteRoom();
		InlineLabel getResult();
		
	
		
		InlineLabel getOrderPersonName();
		
		InlineLabel getOrderPersonSex();
		

		
		InlineLabel getOrderPersonPhone();
		
		CheckBox getOtherPersonCheckbox();
		
		InlineLabel getContactPersonName();
		
		InlineLabel getContactPersonSex();
		InlineLabel getPlaceOrderTime();
		
		InlineLabel getContactPersonPhone();
		
		InlineLabel getMemo();
		
		void setDataCount(String text);
		void setBreadCrumbs(Widget breadCrumbs);
		
		Panel getResultPanel();
		Panel getResultpage();
	}
}
