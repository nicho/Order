package com.omdasoft.orderonline.gwt.order.client.orderSubmit.view;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.presenter.OrderSubmitPresenter.OrderSubmitDisplay;
import com.omdasoft.orderonline.gwt.order.util.DateTool;

public class OrderSubmitWidget extends Composite implements OrderSubmitDisplay {
	@UiField
	InlineLabel city;
	@UiField
	InlineLabel restaurant;
	@UiField
	ListBox reserveTime;
	@UiField
	ListBox reserveTimeH;
	@UiField
	ListBox reserveTimeS;
	@UiField
	TextBox amountOfClient;
	@UiField
	RadioButton favoriteRoom1;
	@UiField
	RadioButton favoriteRoom2;
	@UiField
	RadioButton favoriteRoom3;
	@UiField
	RadioButton favoriteRoom4;
	@UiField
	TextBox orderPersonName;
	@UiField
	RadioButton sex1;
	@UiField
	RadioButton sex2;
	@UiField
	TextBox orderPersonPhone;
	@UiField
	CheckBox otherPersonCheckbox;
	@UiField
	TextBox contactPersonName;
	@UiField
	RadioButton sex3;
	@UiField
	RadioButton sex4;
	@UiField
	TextBox contactPersonPhone;
	@UiField
	TextArea memo;
	@UiField
	Image addBtn;


	
	private static OrderSubmitWidgetUiBinder uiBinder = GWT
			.create(OrderSubmitWidgetUiBinder.class);

	interface OrderSubmitWidgetUiBinder extends UiBinder<Widget, OrderSubmitWidget> {
	}


	public OrderSubmitWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
		Date nowDate=new Date();
		for (int i = 0; i < 10; i++) {
			reserveTime.addItem(DateTool.dateToString(DateTool.addSomeDay(nowDate, i)),DateTool.dateToString(DateTool.addSomeDay(nowDate, i)));
		}
		for (int i = 10; i <= 21; i++) {
			reserveTimeH.addItem(i+"点",i+"");
		}
		for (int i = 0; i <= 3; i++) {
			if(i==0)
				reserveTimeS.addItem("00分","0");
			else
				reserveTimeS.addItem((i*15)+"分",(i*15)+"");
		}
	}

	@Override
	public HasClickHandlers getAddBtnClickHandlers() {
		return addBtn;
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
	public ListBox getReserveTime() {

		return reserveTime;
	}

	@Override
	public ListBox getReserveTimeH() {

		return reserveTimeH;
	}

	@Override
	public ListBox getReserveTimeS() {

		return reserveTimeS;
	}

	@Override
	public TextBox getAmountOfClient() {

		return amountOfClient;
	}

	@Override
	public RadioButton getFavoriteRoom1() {

		return favoriteRoom1;
	}

	@Override
	public RadioButton getFavoriteRoom2() {

		return favoriteRoom2;
	}

	@Override
	public RadioButton getFavoriteRoom3() {

		return favoriteRoom3;
	}

	@Override
	public RadioButton getFavoriteRoom4() {

		return favoriteRoom4;
	}

	@Override
	public TextBox getOrderPersonName() {

		return orderPersonName;
	}

	@Override
	public RadioButton getSex1() {

		return sex1;
	}

	@Override
	public RadioButton getSex2() {

		return sex2;
	}

	@Override
	public TextBox getOrderPersonPhone() {

		return orderPersonPhone;
	}

	@Override
	public CheckBox getOtherPersonCheckbox() {

		return otherPersonCheckbox;
	}

	@Override
	public TextBox getContactPersonName() {

		return contactPersonName;
	}

	@Override
	public RadioButton getSex3() {

		return sex3;
	}

	@Override
	public RadioButton getSex4() {

		return sex4;
	}

	@Override
	public TextBox getContactPersonPhone() {

		return contactPersonPhone;
	}

	@Override
	public TextArea getMemo() {

		return memo;
	}





}
