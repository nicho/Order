package com.omdasoft.orderonline.gwt.order.client.frontOrderList.view;


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
import com.omdasoft.orderonline.gwt.order.client.frontOrderList.presenter.FrontOrderListPresenter.FrontOrderListDisplay;

public class FrontOrderListWidget extends Composite implements FrontOrderListDisplay {

	@UiField
	TextBox queryphone;


	@UiField
	Button searchBtn;
	
	@UiField
	InlineLabel dataCount;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	@UiField
	ListBox pageNumber;
	
	private static FrontOrderListWidgetUiBinder uiBinder = GWT
			.create(FrontOrderListWidgetUiBinder.class);

	interface FrontOrderListWidgetUiBinder extends
			UiBinder<Widget, FrontOrderListWidget> {
	}

	public FrontOrderListWidget() {
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
	public HasValue<String> getPhone() {
		return this.queryphone;
	}

	@Override
	public void setDataCount(String text) {
		dataCount.setText(text);
		
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
	public TextBox getQueryphone() {
		return queryphone;
	}









}
