package com.omdasoft.orderonline.gwt.order.client.orderView.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.orderView.presenter.OrderViewPresenter.OrderViewDisplay;

public class OrderViewWidget extends Composite implements OrderViewDisplay {
	@UiField
	InlineLabel city;
	@UiField
	InlineLabel restaurant;
	@UiField
	InlineLabel reserveTime;
	@UiField
	InlineLabel amountOfClient;
	@UiField
	InlineLabel favoriteRoom;

	@UiField
	InlineLabel orderPersonName;
	@UiField
	InlineLabel orderPersonSex;
	@UiField
	InlineLabel orderPersonPhone;

	@UiField
	CheckBox otherPersonCheckbox;

	@UiField
	InlineLabel contactPersonName;
	@UiField
	InlineLabel contactPersonSex;
	@UiField
	InlineLabel contactPersonPhone;

	@UiField
	InlineLabel memo;
	@UiField
	InlineLabel result;
	@UiField
	InlineLabel dataCount;
	@UiField
	InlineLabel placeOrderTime;
	
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	@UiField
	Panel breadCrumbs;
	private static OrderViewWidgetUiBinder uiBinder = GWT
			.create(OrderViewWidgetUiBinder.class);

	interface OrderViewWidgetUiBinder extends UiBinder<Widget, OrderViewWidget> {
	}

	public OrderViewWidget() {
		initWidget(uiBinder.createAndBindUi(this));

	}



	@Override
	public InlineLabel getCity() {
		return city;
	}

	@Override
	public InlineLabel getrestaurant() {

		return restaurant;
	}

	@Override
	public InlineLabel getReserveTime() {

		return reserveTime;
	}

	@Override
	public InlineLabel getAmountOfClient() {

		return amountOfClient;
	}

	@Override
	public InlineLabel getFavoriteRoom() {

		return favoriteRoom;
	}

	@Override
	public InlineLabel getOrderPersonName() {

		return orderPersonName;
	}

	@Override
	public InlineLabel getOrderPersonSex() {

		return orderPersonSex;
	}

	@Override
	public InlineLabel getOrderPersonPhone() {

		return orderPersonPhone;
	}

	@Override
	public CheckBox getOtherPersonCheckbox() {

		return otherPersonCheckbox;
	}

	@Override
	public InlineLabel getContactPersonName() {

		return contactPersonName;
	}

	@Override
	public InlineLabel getContactPersonSex() {

		return contactPersonSex;
	}

	@Override
	public InlineLabel getContactPersonPhone() {

		return contactPersonPhone;
	}

	@Override
	public InlineLabel getMemo() {

		return memo;
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
	public void setDataCount(String text) {
		dataCount.setText(text);
		
	}



	@Override
	public InlineLabel getResult() {
		return result;
	}



	@Override
	public InlineLabel getPlaceOrderTime() {
		return placeOrderTime;
	}
}
