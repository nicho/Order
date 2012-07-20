package com.omdasoft.orderonline.gwt.order.client.ordersDishes.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.OrdersDishesPresenter.OrdersDishesDisplay;
import com.omdasoft.orderonline.gwt.order.client.view.constant.CssStyleConstants;

public class OrdersDishesWidget extends Composite implements
		OrdersDishesDisplay {

	@UiField
	Image returnBtn;
	@UiField
	Image addBtn;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	@UiField
	Panel tabpage;

	@UiField
	Panel bookingPanel;

	@UiField
	InlineLabel bookingTitle;

	@UiField
	InlineLabel menutypeAll;
	
	@UiField
	InlineLabel city;
	@UiField
	InlineLabel restaurant;
	@UiField
	InlineLabel orderUser;
	@UiField
	InlineLabel number;
	@UiField
	InlineLabel room;
	@UiField
	InlineLabel dishesNumber;
	@UiField
	InlineLabel dishesMoney;
	
	@UiField
	InlineLabel phone;
	@UiField
	ListBox pageNumber;
	@UiField
	Image ordernow;
	
	@UiField
	TextBox queryKey;
	@UiField
	Anchor querybtn;
	
	
	private static OrdersDishesWidgetUiBinder uiBinder = GWT
			.create(OrdersDishesWidgetUiBinder.class);

	interface OrdersDishesWidgetUiBinder extends
			UiBinder<Widget, OrdersDishesWidget> {
	}

	public OrdersDishesWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		pageNumber.clear();
		pageNumber.addItem("12","12");

	}
	@Override
	public ListBox getPageNumber() {
		return pageNumber;
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
	public Panel getBookingPanel() {
		return bookingPanel;
	}


	@Override
	public void setCity(String text) {
		city.setText(text);
	}

	@Override
	public void setRestaurant(String text) {
		restaurant.setText(text);
	}

	@Override
	public void setOrderUser(String text) {
		orderUser.setText(text);
	}

	@Override
	public void setNumber(String text) {
		number.setText(text);
	}

	@Override
	public void setRoom(String text) {
		room.setText(text);
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
	public void hiddenDishesNumber(boolean fal) {
		if(fal==true)
			dishesNumber.getElement().getParentElement().addClassName(CssStyleConstants.hidden);
		else
			dishesNumber.getElement().getParentElement().setClassName("");
	}


	@Override
	public void setBookingTitle(String text) {
		bookingTitle.setText(text);
	}
	@Override
	public InlineLabel getMenutypeAll() {
		return menutypeAll;
	}
	@Override
	public void setphone(String text) {
		phone.setText(text);
		
	}
	@Override
	public Image getOrdernow() {
		return ordernow;
	}
	@Override
	public TextBox getQueryKey() {
		return queryKey;
	}
	@Override
	public Anchor getQuerybtn() {
		return querybtn;
	}

}
