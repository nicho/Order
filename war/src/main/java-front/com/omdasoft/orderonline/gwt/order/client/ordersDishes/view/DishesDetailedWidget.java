package com.omdasoft.orderonline.gwt.order.client.ordersDishes.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.Elt;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.DishesDetailedPresenter.DishesDetailedDisplay;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class DishesDetailedWidget extends Composite implements
		DishesDetailedDisplay {
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

	public DishesDetailedWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public DishesDetailedWidget(String dishesName, String price,
			String description, String photo) {
		initWidget(uiBinder.createAndBindUi(this));
		this.dishesName.setText(dishesName);
		this.price.setText(price);
		this.description.setText(description);
		if(!StringUtil.isEmpty(photo))
		this.photo.setUrl(StringUtil.getCompleteImageUrl(photo));
	}

	@Override
	public void setName(String text) {

		this.dishesName.setText(text);
	}

	@Override
	public void setPrice(String text) {
		this.price.setText(text);

	}

	@Override
	public void setDescription(String text) {
		this.description.setText(text);

	}

	@Override
	public void setPhoto(String text) {
		this.photo.setUrl(StringUtil.getCompleteImageUrl(text));

	}

}
