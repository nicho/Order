package com.omdasoft.orderonline.gwt.order.client.ordersDishes.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.OrdersDishesPresenter;

public class DishesTypeLatticeTooWidget extends Composite {

	@UiField
	Anchor name;
	
	private static AwardShopLatticeTooWidgetUiBinder uiBinder = GWT
			.create(AwardShopLatticeTooWidgetUiBinder.class);

	interface AwardShopLatticeTooWidgetUiBinder extends
			UiBinder<Widget, DishesTypeLatticeTooWidget> {
	}

	public DishesTypeLatticeTooWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public DishesTypeLatticeTooWidget(final String id, final String name,final OrdersDishesPresenter ordersDishesPresenter) {
		initWidget(uiBinder.createAndBindUi(this));
		this.name.setText(name);
		this.name.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ordersDishesPresenter.refulDishes(id);
				
			}
		});

	}

}
