package com.omdasoft.orderonline.gwt.order.client.ordersDishes.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.OrdersDishesPresenter.OrdersDishesDisplay;

public class OrdersDishesWidget extends Composite implements
		OrdersDishesDisplay {

	@UiField
	Button returnBtn;
	@UiField
	Button addBtn;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	@UiField
	Panel tabpage;
	@UiField
	Panel detailPanel;
	@UiField
	Panel bookingPanel;
	
	private static OrdersDishesWidgetUiBinder uiBinder = GWT
			.create(OrdersDishesWidgetUiBinder.class);

	interface OrdersDishesWidgetUiBinder extends
			UiBinder<Widget, OrdersDishesWidget> {
	}

	public OrdersDishesWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getAddBtnClickHandlers() {
		return this.addBtn;
	}
	@Override
	public HasClickHandlers getReturnBtnClickHandlers() {
		return this.returnBtn;
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
	public Panel getTabpage() {
		return tabpage;
	}

	@Override
	public Panel getDetailPanel() {
		return detailPanel;
	}

	@Override
	public Panel getBookingPanel() {
		return bookingPanel;
	}

}
