package com.omdasoft.orderonline.gwt.order.client.orderList.view;


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
import com.omdasoft.orderonline.gwt.order.client.orderList.presenter.OrderListPresenter.OrderListDisplay;

public class OrderListWidget extends Composite implements OrderListDisplay {

	@UiField
	TextBox staffNameorNo;
	@UiField
	ListBox staffStatus;

	
	@UiField
	Button addOrderBtn;

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
	
	private static OrderListWidgetUiBinder uiBinder = GWT
			.create(OrderListWidgetUiBinder.class);

	interface OrderListWidgetUiBinder extends
			UiBinder<Widget, OrderListWidget> {
	}

	public OrderListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getSearchBtnClickHandlers() {
		return this.searchBtn;
	}


	@Override
	public HasClickHandlers getAddOrderBtnClickHandlers() {
		return this.addOrderBtn;
	}

	@Override
	public void initStaffStatus() {
		staffStatus.addItem("不限", "ALL");
		staffStatus.addItem("待处理", "UNHANDLED");
		staffStatus.addItem("成功", "SUCCESS");
		staffStatus.addItem("失败", "FAILURE");
		staffStatus.addItem("未消费", "NOTCONSUMPR");
		staffStatus.addItem("已消费", "HASCONSUMER");
		

		
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








}
