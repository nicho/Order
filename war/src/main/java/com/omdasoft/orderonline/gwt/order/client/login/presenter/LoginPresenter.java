package com.omdasoft.orderonline.gwt.order.client.login.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

/**
 * Login Presenter, this will emit a LoginEvent upon the login button being clicked.
 * 
 * @author kmtong
 * 
 */
public interface LoginPresenter extends Presenter<LoginPresenter.LoginDisplay> {

	/**
	 * Logical UI structure for the Login Window/Widget
	 * 
	 * @author kmtong
	 * 
	 */
	public static interface LoginDisplay extends Display {

		HasClickHandlers getLoginClickHandlers();
		HasClickHandlers getLicenseClickHandlers();

		HasValue<String> getUsername();
		PasswordTextBox getPasswordBox();
		HasValue<String> getPassword();
		public void changeImage();

		public HasKeyUpHandlers getVerifyCodeKeyUpHandlers();

		public HasValue<String> getVerifyCode();
		
		void setMessage(String text);
	}

}
