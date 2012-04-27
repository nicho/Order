package com.omdasoft.orderonline.gwt.order.client.userAdd.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface UserAddPresenter extends
		Presenter<UserAddPresenter.UserAddDisplay> {

	public void initStaffUpdate(String staffId);

	public static interface UserAddDisplay extends Display {

		public HasClickHandlers getAddBtnClickHandlers();

		public HasClickHandlers getImportBtnClickHandlers();

		void setBreadCrumbs(Widget breadCrumbs);

		String getStaffNo();

		String getStaffName();
		void enableStaffName();

		String getPhone();
		PasswordTextBox getPwd();
		PasswordTextBox getPwd2();



	
		void setStaffNo(String text);

		void setStaffName(String text);


		void setPhone(String text);

	

	
	
		void setTitleText(String text);
		CheckBox getAdmin();
		CheckBox getGift();
	}
}
