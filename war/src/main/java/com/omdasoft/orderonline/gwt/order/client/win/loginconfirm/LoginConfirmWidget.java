package com.omdasoft.orderonline.gwt.order.client.win.loginconfirm;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

public class LoginConfirmWidget extends Composite implements
		LoginConfirmPresenter.LoginConfirmDisplay {

	@UiField
	RadioButton hr_type;
	@UiField
	RadioButton dept_type;
	@UiField
	Button ok;


	interface LoginConfirmWidgetUiBinder extends UiBinder<Widget, LoginConfirmWidget> {

	}

	public static LoginConfirmWidgetUiBinder uiBinder = GWT
			.create(LoginConfirmWidgetUiBinder.class);

	public LoginConfirmWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getOkBtn() {
		return ok;
	}

	@Override
	public String getLoginType() {
		if(hr_type.getValue())
			return "CORP_ADMIN";
		else if(dept_type.getValue())
			return "DEPT_MGR";
		return null;
	}

}
