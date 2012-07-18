package com.omdasoft.orderonline.gwt.order.client.ordersLogin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.presenter.OrdersLoginPresenter.OrdersLoginDisplay;

public class OrdersLoginWidget extends Composite implements
	OrdersLoginDisplay {
	@UiField
	ListBox city;
	@UiField
	ListBox restaurant;
	@UiField
	TextBox phone;
	@UiField
	Image submitBtn;
	
	private static OrdersLoginWidgetUiBinder uiBinder = GWT
			.create(OrdersLoginWidgetUiBinder.class);

	interface OrdersLoginWidgetUiBinder extends
			UiBinder<Widget, OrdersLoginWidget> {
	}

	public OrdersLoginWidget() {
		initWidget(uiBinder.createAndBindUi(this));

	}
	@Override
	public ListBox getCity() {
		return city;
	}

	@Override
	public ListBox getrestaurant() {
		return restaurant;
	}

	@Override
	public TextBox getPhone() {
		return phone;
	}

	@Override
	public Image getSubmitBtn() {
		return submitBtn;
	}
	
}
