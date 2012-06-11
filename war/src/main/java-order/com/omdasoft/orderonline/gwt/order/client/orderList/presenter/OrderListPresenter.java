package com.omdasoft.orderonline.gwt.order.client.orderList.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface OrderListPresenter extends Presenter<OrderListPresenter.OrderListDisplay> {
	

	public static interface OrderListDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();
		public HasClickHandlers getAddOrderBtnClickHandlers();
		void initStaffStatus();
		String getSttaffStatus();

		HasValue<String> getStaffNameorNo();

		void setDataCount(String text);
		void setBreadCrumbs(Widget breadCrumbs);
		
		Panel getResultPanel();
		Panel getResultpage();
		public ListBox getPageNumber();

		Anchor getDate1();
		Anchor getDate2();
		Anchor getDate3();
		Anchor getDate4();
		Anchor getDate5();
		Anchor getNoDate();
		
		Anchor getDay1();
		Anchor getDay2();
		Anchor getDay3();
		Anchor getDay4();
		
		DateBox getDateStart();
		DateBox getDateEnd();
	}
}
