package com.omdasoft.orderonline.gwt.order.client.userView.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface UserViewPresenter extends
		Presenter<UserViewPresenter.UserViewDisplay> {

	public void initUserView(String staffId);
	public void initUserView_Colleague(String staffId,boolean colleague);
	public static interface UserViewDisplay extends Display {

		public HasClickHandlers getupadateBtnClickHandlers();

		void setBreadCrumbs(Widget breadCrumbs);

		void setStaffNo(String text);

		void setStaffName(String text);



		void setPhone(String text);

	
	
		InlineLabel getStaffRoles();

	
	
		void displayUpdateBtn(boolean colleague);
	}
}
