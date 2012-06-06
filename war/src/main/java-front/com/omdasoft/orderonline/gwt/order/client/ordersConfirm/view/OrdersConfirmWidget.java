package com.omdasoft.orderonline.gwt.order.client.ordersConfirm.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter.OrdersConfirmPresenter.OrdersConfirmDisplay;

public class OrdersConfirmWidget extends Composite implements
	OrdersConfirmDisplay {
	
	@UiField
	Panel bookingPanel;
	@UiField
	InlineLabel dishesNumber;
	@UiField
	InlineLabel dishesMoney;
	@UiField
	Image nextBtn;
	
	private static OrdersConfirmWidgetUiBinder uiBinder = GWT
			.create(OrdersConfirmWidgetUiBinder.class);

	interface OrdersConfirmWidgetUiBinder extends
			UiBinder<Widget, OrdersConfirmWidget> {
	}

	public OrdersConfirmWidget() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public Panel getBookingPanel() {
		return bookingPanel;
	}

	@Override
	public void setDishesNumber(String text) {
		dishesNumber.setText(text);
	}

	@Override
	public void setDishesMoney(String text) {
		dishesMoney.setText(text);
	}

	@Override
	public Image getNextBtn() {
		return nextBtn;
	}
	
}
