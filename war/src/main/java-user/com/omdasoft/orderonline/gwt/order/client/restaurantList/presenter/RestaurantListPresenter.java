package com.omdasoft.orderonline.gwt.order.client.restaurantList.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface RestaurantListPresenter extends Presenter<RestaurantListPresenter.RestaurantListDisplay> {
	

	public static interface RestaurantListDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();
		public HasClickHandlers getAddBtnClickHandlers();
		void initStaffStatus();
		String getSttaffStatus();
		String getSttaffRole();
		HasValue<String> getStaffNameorNo();

		void setDataCount(String text);
		void setBreadCrumbs(Widget breadCrumbs);
		
		Panel getResultPanel();
		Panel getResultpage();

		public ListBox getPageNumber();

	}
}
