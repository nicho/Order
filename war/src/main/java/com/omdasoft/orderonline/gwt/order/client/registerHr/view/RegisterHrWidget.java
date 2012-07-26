package com.omdasoft.orderonline.gwt.order.client.registerHr.view;


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
import com.omdasoft.orderonline.gwt.order.client.registerHr.presenter.RegisterHrPresenter.RegisterHrDisplay;

public class RegisterHrWidget extends Composite implements RegisterHrDisplay {

	@UiField
	Button hrRegisterbutton;
	@UiField
	TextBox email;
	@UiField
	TextBox username;
	@UiField
	PasswordTextBox password;
	@UiField
	PasswordTextBox validatePassword;
 
	@UiField
	TextBox tell;
	
	
	
	private static RegisterHrWidgetUiBinder uiBinder = GWT
			.create(RegisterHrWidgetUiBinder.class);

	interface RegisterHrWidgetUiBinder extends
			UiBinder<Widget, RegisterHrWidget> {
	}

	public RegisterHrWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@Override
	public HasClickHandlers getRegisterHrClickHandlers() {
		return hrRegisterbutton;
	}

	@Override
	public HasValue<String> getUsername() {
		return username;
	}

	@Override
	public HasValue<String> getPassword() {
		return password;
	}

	@Override
	public HasValue<String> getTell() {
		return tell;
	}

 

	@Override
	public HasValue<String> getEmail() {
		return email;
	}

	@Override
	public HasValue<String> getValidatePassword() {
		return validatePassword;
	}


	

}
