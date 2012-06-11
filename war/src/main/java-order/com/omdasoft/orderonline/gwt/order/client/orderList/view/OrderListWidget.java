package com.omdasoft.orderonline.gwt.order.client.orderList.view;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.omdasoft.orderonline.gwt.order.client.core.view.constant.ViewConstants;
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
	@UiField
	ListBox pageNumber;
	@UiField
	Anchor date1;
	@UiField
	Anchor date2;
	@UiField
	Anchor date3;
	@UiField
	Anchor date4;
	@UiField
	Anchor date5;
	@UiField
	Anchor day1;
	@UiField
	Anchor day2;
	@UiField
	Anchor day3;
	@UiField
	Anchor day4;
	@UiField
	Anchor nodate;
	@UiField
	DateBox dateStart;
	@UiField
	DateBox dateEnd;
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static OrderListWidgetUiBinder uiBinder = GWT
			.create(OrderListWidgetUiBinder.class);

	interface OrderListWidgetUiBinder extends
			UiBinder<Widget, OrderListWidget> {
	}

	public OrderListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		dateStart.setFormat(new DateBox.DefaultFormat(dateFormat));
		dateEnd.setFormat(new DateBox.DefaultFormat(dateFormat));
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
		staffStatus.addItem("已取消", "HASCANCEL");

		
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
	public Anchor getDate1() {
		return date1;
	}
	@Override
	public Anchor getDate2() {
		return date2;
	}
	@Override
	public Anchor getDate3() {
		return date3;
	}
	@Override
	public Anchor getDate4() {
		return date4;
	}
	@Override
	public Anchor getDate5() {
		return date5;
	}
	@Override
	public Anchor getDay1() {
		return day1;
	}
	@Override
	public Anchor getDay2() {
		return day2;
	}
	@Override
	public Anchor getDay3() {
		return day3;
	}
	@Override
	public Anchor getDay4() {
		return day4;
	}
	@Override
	public DateBox getDateStart() {
		return dateStart;
	}
	@Override
	public DateBox getDateEnd() {
		return dateEnd;
	}
	@Override
	public Anchor getNoDate() {
		return nodate;
	}








}
