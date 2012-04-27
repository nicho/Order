package com.omdasoft.orderonline.gwt.order.client.userView.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.userView.presenter.UserViewPresenter.UserViewDisplay;
import com.omdasoft.orderonline.gwt.order.client.view.constant.CssStyleConstants;

public class UserViewWidget extends Composite implements UserViewDisplay {

	@UiField
	InlineLabel staffNo;
	@UiField
	InlineLabel staffName;

	@UiField
	InlineLabel phone;

	@UiField
	InlineLabel staffRoles;
	
	@UiField
	Button updateBtn;

	@UiField
	Panel breadCrumbs;

	private static UserViewWidgetUiBinder uiBinder = GWT
			.create(UserViewWidgetUiBinder.class);

	interface UserViewWidgetUiBinder extends UiBinder<Widget, UserViewWidget> {
	}

	public UserViewWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);

	}

	@Override
	public HasClickHandlers getupadateBtnClickHandlers() {
		return updateBtn;
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
	public void displayUpdateBtn(boolean colleague) {
		if(colleague==true)
		{
			if(updateBtn.getElement().getParentElement()!=null)
				updateBtn.getElement().getParentElement().setClassName(CssStyleConstants.hidden);
		}
	}

	@Override
	public InlineLabel getStaffRoles() {
		return staffRoles;
	}

}
