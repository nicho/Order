package com.omdasoft.orderonline.gwt.order.client.orderList.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
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



	}
}
