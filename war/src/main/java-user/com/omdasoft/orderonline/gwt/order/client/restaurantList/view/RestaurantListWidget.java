package com.omdasoft.orderonline.gwt.order.client.restaurantList.view;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.presenter.RestaurantListPresenter.RestaurantListDisplay;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;

public class RestaurantListWidget extends Composite implements RestaurantListDisplay {

	@UiField
	TextBox staffNameorNo;
	@UiField
	ListBox staffStatus;
	@UiField
	ListBox staffRole;
	
	@UiField
	Button addBtn;

	@UiField
	Button searchBtn;
	
	@UiField
	InlineLabel dataCount;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	@UiField
	Panel breadCrumbs;
	@UiField
	ListBox pageNumber;
	private static RestaurantListWidgetUiBinder uiBinder = GWT
			.create(RestaurantListWidgetUiBinder.class);

	interface RestaurantListWidgetUiBinder extends
			UiBinder<Widget, RestaurantListWidget> {
	}

	public RestaurantListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		pageNumber.clear();
		pageNumber.addItem("10","10");
		pageNumber.addItem("20","20");
		pageNumber.addItem("50","50");
		pageNumber.addItem("100","100");
	}
	@Override
	public ListBox getPageNumber() {
		return pageNumber;
	}

	@Override
	public HasClickHandlers getSearchBtnClickHandlers() {
		return this.searchBtn;
	}


	@Override
	public HasClickHandlers getAddBtnClickHandlers() {
		return this.addBtn;
	}

	@Override
	public void initStaffStatus() {
		staffStatus.addItem("不限", "ALL");
		staffStatus.addItem("待入职", "ENTRY");
		staffStatus.addItem("在职", "JOB");
		staffStatus.addItem("已离职", "DEPARTURE");
		
		staffRole.addItem("不限", "ALL");
		staffRole.addItem("HR管理员", UserRoleVo.CORP_ADMIN.toString());

		
	}

	@Override
	public HasValue<String> getStaffNameorNo() {
		return this.staffNameorNo;
	}

	@Override
	public void setDataCount(String text) {
		dataCount.setText(text);
		
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
		
	}

	@Override
	public Panel getResultPanel() {
		return this.resultPanel;
	}

	@Override
	public Panel getResultpage() {
		return this.resultpage;
	}

	@Override
	public String getSttaffStatus() {
		return staffStatus.getValue(staffStatus.getSelectedIndex());
	}
	@Override
	public String getSttaffRole() {
		return staffRole.getValue(staffRole.getSelectedIndex());
	}


}
