package com.omdasoft.orderonline.gwt.order.client.ordersConfirm.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter.OrdersConfirmPresenter.OrdersConfirmDisplay;

public class OrdersConfirmWidget extends Composite implements
	OrdersConfirmDisplay {
	

	
	private static OrdersConfirmWidgetUiBinder uiBinder = GWT
			.create(OrdersConfirmWidgetUiBinder.class);

	interface OrdersConfirmWidgetUiBinder extends
			UiBinder<Widget, OrdersConfirmWidget> {
	}

	public OrdersConfirmWidget() {
		initWidget(uiBinder.createAndBindUi(this));

	}


	
}
