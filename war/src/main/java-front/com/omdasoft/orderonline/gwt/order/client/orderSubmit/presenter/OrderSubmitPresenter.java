package com.omdasoft.orderonline.gwt.order.client.orderSubmit.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface OrderSubmitPresenter extends Presenter<OrderSubmitPresenter.OrderSubmitDisplay> {
	

public void initOrderPhone(String phone);

	public static interface OrderSubmitDisplay extends Display {

		public HasClickHandlers getAddBtnClickHandlers();
	
		InlineLabel getCity();
		
		InlineLabel getrestaurant();
		
		ListBox getReserveTime();
		
		ListBox getReserveTimeH();
		
		ListBox getReserveTimeS();
		
		TextBox getAmountOfClient();
		
		RadioButton getFavoriteRoom1();
		
		RadioButton getFavoriteRoom2();
		
		RadioButton getFavoriteRoom3();
		
		RadioButton getFavoriteRoom4();
		
		TextBox getOrderPersonName();
		
		RadioButton getSex1();
		
		RadioButton getSex2();
		
		TextBox getOrderPersonPhone();
		
		CheckBox getOtherPersonCheckbox();
		
		TextBox getContactPersonName();
		
		RadioButton getSex3();
		
		RadioButton getSex4();
		
		TextBox getContactPersonPhone();
		
		TextArea getMemo();

	}
}
