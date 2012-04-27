package com.omdasoft.orderonline.gwt.order.client.ordersDishes.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

public class DishesDetailedWidget extends Composite {
	@UiField
	InlineLabel dishesName;
	@UiField
	InlineLabel price;
	@UiField
	InlineLabel description;
	@UiField
	Image photo;
	private static DishesDetailedWidgetUiBinder uiBinder = GWT
			.create(DishesDetailedWidgetUiBinder.class);

	interface DishesDetailedWidgetUiBinder extends
			UiBinder<Widget, DishesDetailedWidget> {
	}

	public DishesDetailedWidget(String dishesName,String price,String description,String photo) {
		initWidget(uiBinder.createAndBindUi(this));
		this.dishesName.setText(dishesName);
		this.price.setText(price);
		this.description.setText(description);
		this.photo.setUrl("imageshow?imageName="+photo);
	}

}
