package com.omdasoft.orderonline.gwt.order.client.orderIndex.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.presenter.OrderIndexPresenter.OrderIndexDisplay;

public class OrderIndexWidget extends Composite implements OrderIndexDisplay {
	@UiField
	Panel dock;

	@UiField
	Anchor adminpage;


	@UiField
	Anchor orderNow;
	@UiField
	Anchor orderindex;
	@UiField
	Anchor queryOrder;
	
	private static OrderIndexWidgetUiBinder uiBinder = GWT
			.create(OrderIndexWidgetUiBinder.class);

	interface OrderIndexWidgetUiBinder extends
			UiBinder<Widget, OrderIndexWidget> {
	}

	public OrderIndexWidget() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public Anchor getAdminpage() {
		return adminpage;
	}

	@Override
	public Panel getDock() {
		return dock;
	}



	@Override
	public Anchor getOrderNow() {
		return orderNow;
	}

	@Override
	public Anchor getOrderIndex() {
		return orderindex;
	}

	@Override
	public Anchor getQueryOrder() {
		return queryOrder;
	}

}
