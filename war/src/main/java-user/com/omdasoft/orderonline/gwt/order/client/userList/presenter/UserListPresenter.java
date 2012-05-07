package com.omdasoft.orderonline.gwt.order.client.userList.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface UserListPresenter extends Presenter<UserListPresenter.UserListDisplay> {
	

	public static interface UserListDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();

		public HasClickHandlers getAddStaffBtnClickHandlers();
		void initStaffStatus();
		String getSttaffStatus();
		String getSttaffRole();
		HasValue<String> getStaffNameorNo();

		void setDataCount(String text);
		void setBreadCrumbs(Widget breadCrumbs);
		
		Panel getResultPanel();
		Panel getResultpage();

		void displayBtn();
		public ListBox getPageNumber();

	}
}
