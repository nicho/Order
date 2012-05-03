package com.omdasoft.orderonline.gwt.order.client.password.view;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.password.presenter.PasswordPresenter.PasswordDisplay;
public class PasswordWidget extends Composite implements PasswordDisplay {

	@UiField
	Button button;
	
	@UiField
	TextBox username;
	@UiField
	PasswordTextBox newpassword;
	@UiField
	PasswordTextBox oldpassword;
	@UiField
	PasswordTextBox validatePassword;
	
	
	private static PasswordWidgetUiBinder uiBinder = GWT.create(PasswordWidgetUiBinder.class);

	interface PasswordWidgetUiBinder extends
			UiBinder<Widget, PasswordWidget> {
	}

	public PasswordWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getPasswordClickHandlers() {
		return button;
	}

	@Override
	public HasValue<String> getUsername() {
		return username;
	}

	@Override
	public HasValue<String> getNewPassword() {
		return newpassword;
	}

	@Override
	public HasValue<String> getOldPassword() {
		return oldpassword;
	}

	@Override
	public HasValue<String> getValidatePassword() {
		return validatePassword;
	}

	@Override
	public void setUsername(String text) {
		username.setText(text);
		
	}


	

}
