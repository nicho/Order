package com.omdasoft.orderonline.gwt.order.client.ordersWait.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.ordersWait.presenter.OrdersWaitPresenter.OrdersWaitDisplay;

public class OrdersWaitWidget extends Composite implements
	OrdersWaitDisplay {
	

	@UiField
	InlineLabel titletext;
	@UiField
	InlineLabel timeText;
	@UiField
	InlineLabel messageText;
	
	private static OrdersWaitWidgetUiBinder uiBinder = GWT
			.create(OrdersWaitWidgetUiBinder.class);

	interface OrdersWaitWidgetUiBinder extends
			UiBinder<Widget, OrdersWaitWidget> {
	}

	public OrdersWaitWidget() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	public InlineLabel getTitletext() {
		return titletext;
	}

	public void setTitletext(InlineLabel titletext) {
		this.titletext = titletext;
	}

	@Override
	public InlineLabel getTimeText() {
		return timeText;
	}

	@Override
	public InlineLabel getMessageText() {
		return messageText;
	}

	
}
