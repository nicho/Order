package com.omdasoft.orderonline.gwt.order.client.company.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.company.presenter.CompanyListPresenter.CompanyListDisplay;

public class CompanyListWidget extends Composite implements CompanyListDisplay {
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	@UiField
	TextBox companyName;
	@UiField
	Button searchBtn;
	@UiField
	Button addCompanyBtn;
	@UiField
	Panel breadCrumbs;
	@UiField
	ListBox pageNumber;
	private static CompanyWidgetUiBinder uiBinder = GWT
			.create(CompanyWidgetUiBinder.class);

	interface CompanyWidgetUiBinder extends UiBinder<Widget, CompanyListWidget> {
	}

	public CompanyListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		pageNumber.clear();
		pageNumber.addItem("10","10");
		pageNumber.addItem("20","20");
		pageNumber.addItem("50","50");
	}

	@Override
	public HasClickHandlers getSearchBtnClickHandlers() {
		return searchBtn;
	}
	@Override
	public HasClickHandlers getAddCompanyBtnClickHandlers() {
		return this.addCompanyBtn;
	}
	@Override
	public Panel getResultPanel() {
		return resultPanel;
	}
	@Override
	public TextBox getCompanyName() {
		return companyName;
	}
	@Override
	public Panel getResultpage() {
		return resultpage;
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);		

	}

	@Override
	public ListBox getPageNumber() {
		return pageNumber;
	}

}
