package com.omdasoft.orderonline.gwt.order.client.registerHr.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface RegisterHrPresenter extends Presenter<RegisterHrPresenter.RegisterHrDisplay> {
	
	public void initRegisterCorp(String corpId);
	public static interface RegisterHrDisplay extends Display {

		public HasClickHandlers getRegisterHrClickHandlers();
		public HasValue<String> getUsername();
		public HasValue<String> getTell();
		public HasValue<String> getEmail();
		public HasValue<String> getPassword();
		public HasValue<String> getValidatePassword();
		

	}
}
