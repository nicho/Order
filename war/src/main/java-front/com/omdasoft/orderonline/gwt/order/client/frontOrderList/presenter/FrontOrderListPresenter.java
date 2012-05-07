package com.omdasoft.orderonline.gwt.order.client.frontOrderList.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface FrontOrderListPresenter extends Presenter<FrontOrderListPresenter.FrontOrderListDisplay> {
	
	public void initFrontOrder(String phone);
	public static interface FrontOrderListDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();

		HasValue<String> getPhone();

		void setDataCount(String text);
		TextBox getQueryphone();
		
		Panel getResultPanel();
		Panel getResultpage();

		public ListBox getPageNumber();

	}
}
