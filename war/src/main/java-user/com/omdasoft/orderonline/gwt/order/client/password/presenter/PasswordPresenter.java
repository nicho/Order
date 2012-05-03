package com.omdasoft.orderonline.gwt.order.client.password.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.omdasoft.orderonline.gwt.order.client.mvp.DialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;

public interface PasswordPresenter extends DialogPresenter<PasswordPresenter.PasswordDisplay> {
	
	   
	    public static interface PasswordDisplay extends Display {
		public void setUsername(String text);
		public HasClickHandlers getPasswordClickHandlers();
		public HasValue<String> getUsername();
		public HasValue<String> getOldPassword();
		public HasValue<String> getNewPassword();
		public HasValue<String> getValidatePassword();
		
	}
}
