package com.omdasoft.orderonline.gwt.order.client.orderSave.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria.OrderStatus;

public interface OrderSavePresenter extends Presenter<OrderSavePresenter.OrderSaveDisplay> {
	
public void initOrder(String orderId);
	public static interface OrderSaveDisplay extends Display {

		public HasClickHandlers getAddBtnClickHandlers();

		ListBox getCity();
		
		ListBox getrestaurant();
		
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
		void setBreadCrumbs(Widget breadCrumbs);
		void setTitleText(String text);
		void initStatus(OrderStatus status);
		void hiddenStatus();
		String getStatus();
	}
}
