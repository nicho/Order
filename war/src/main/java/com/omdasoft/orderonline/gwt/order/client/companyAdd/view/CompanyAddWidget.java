package com.omdasoft.orderonline.gwt.order.client.companyAdd.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.presenter.CompanyAddPresenter.CompanyAddDisplay;

public class CompanyAddWidget extends Composite implements CompanyAddDisplay {
	@UiField
	InlineLabel titleText;
	@UiField
	TextBox enterpriseName;
	@UiField
	TextBox address;
	@UiField
	TextBox linkman;
	@UiField
	TextBox tell;
	@UiField
	TextBox email;
	@UiField
	TextBox callphone;
	@UiField
	TextBox cid;
	
	@UiField
	Button addBtn;
	@UiField
	Panel breadCrumbs;
	
	private static CompanyAddWidgetUiBinder uiBinder = GWT
			.create(CompanyAddWidgetUiBinder.class);

	interface CompanyAddWidgetUiBinder extends UiBinder<Widget, CompanyAddWidget> {
	}

	public CompanyAddWidget() {
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
	
	public String getEnterpriseName() {
		return enterpriseName.getValue();
	}

	public void setEnterpriseName(String name) {
		this.enterpriseName.setValue(name);
	}
	
	public String getAddress() {
		return address.getValue();
	}

	public void setAddress(String address) {
		this.address.setValue(address);
	}

	public String getLinkman() {
		return linkman.getValue();
	}

	public void setLinkman(String linkman) {
		this.linkman.setValue(linkman);
	}

	public String getTell() {
		return tell.getValue();
	}

	public void setTell(String tell) {
		this.tell.setValue(tell);
	}

	public String getEmail() {
		return email.getValue();
	}

	public void setEmail(String email) {
		this.email.setValue(email);
	}

	public String getCallphone() {
		return callphone.getValue();
	}

	public void setCallphone(String callphone) {
		this.callphone.setValue(callphone);
	}



	
	@Override
	public void setTitleText(String text) {
		titleText.setText(text);
	}

	@Override
	public void setAddBtnValue(String text) {
		this.addBtn.setText(text);
	}

	@Override
	public TextBox getCid() {
 		return cid;
	}



}
