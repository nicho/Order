package com.omdasoft.orderonline.gwt.order.client.ordersLogin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.presenter.OrdersLoginPresenter.OrdersLoginDisplay;

public class OrdersLoginWidget extends Composite implements
	OrdersLoginDisplay {

	private static OrdersLoginWidgetUiBinder uiBinder = GWT
			.create(OrdersLoginWidgetUiBinder.class);

	interface OrdersLoginWidgetUiBinder extends
			UiBinder<Widget, OrdersLoginWidget> {
	}

	public OrdersLoginWidget() {
		initWidget(uiBinder.createAndBindUi(this));

	}
	
}
