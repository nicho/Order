package com.omdasoft.orderonline.gwt.order.client.ordersConfirm.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter.KwPresenter.KwDisplay;

public class KwWidget extends Composite implements KwDisplay {
	@UiField
	Panel checkBoxPanel;
	@UiField
	Button buttonSelect;
	private static KwWidgetUiBinder uiBinder = GWT
			.create(KwWidgetUiBinder.class);

	interface KwWidgetUiBinder extends UiBinder<Widget, KwWidget> {
	}

	public KwWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public KwWidget(String dishesName, String price, String description,
			String photo) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Panel getCheckBoxPanel() {
		return checkBoxPanel;
	}

	@Override
	public Button getButtonSelect() {
		return buttonSelect;
	}

}
