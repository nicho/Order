package com.omdasoft.orderonline.gwt.order.client.userAdd.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.core.view.constant.ViewConstants;
import com.omdasoft.orderonline.gwt.order.client.userAdd.presenter.UserAddPresenter.UserAddDisplay;

public class UserAddWidget extends Composite implements UserAddDisplay {
	@UiField
	InlineLabel titleText;
	@UiField
	TextBox staffNo;
	@UiField
	TextBox staffName;

	@UiField
	TextBox phone;
	@UiField
	PasswordTextBox pwd;
	@UiField
	PasswordTextBox pwd2;
	
	


	@UiField
	Button addBtn;
	@UiField
	Button importBtn;

	@UiField
	Panel breadCrumbs;

	@UiField
	CheckBox admin;
	@UiField
	CheckBox gift;
	
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static UserAddWidgetUiBinder uiBinder = GWT
			.create(UserAddWidgetUiBinder.class);

	interface UserAddWidgetUiBinder extends UiBinder<Widget, UserAddWidget> {
	}

	public UserAddWidget() {

		initWidget(uiBinder.createAndBindUi(this));
		
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);

	}

	@Override
	public HasClickHandlers getAddBtnClickHandlers() {
		return addBtn;
	}

	@Override
	public HasClickHandlers getImportBtnClickHandlers() {
		return importBtn;
	}

	@Override
	public String getStaffNo() {
		return staffNo.getValue();
	}

	@Override
	public String getStaffName() {
		return this.staffName.getValue();
	}



	@Override
	public String getPhone() {
		return this.phone.getValue();
	}



	@Override
	public void setStaffNo(String text) {
		this.staffNo.setText(text);
	}

	@Override
	public void setStaffName(String text) {
		this.staffName.setText(text);
	}


	
	

	@Override
	public void setPhone(String text) {
		this.phone.setText(text);
	}




	@Override
	public void setTitleText(String text) {
		titleText.setText(text);
	}



	@Override
	public CheckBox getAdmin() {
		return admin;
	}

	@Override
	public CheckBox getGift() {
		return gift;
	}

	@Override
	public PasswordTextBox getPwd() {
		return pwd;
	}

	@Override
	public PasswordTextBox getPwd2() {
		return pwd2;
	}

	@Override
	public void enableStaffName() {
		staffName.setEnabled(false);
		
	}
}
