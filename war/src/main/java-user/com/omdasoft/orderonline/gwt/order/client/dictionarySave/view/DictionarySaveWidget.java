package com.omdasoft.orderonline.gwt.order.client.dictionarySave.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.presenter.DictionarySavePresenter.DictionarySaveDisplay;

public class DictionarySaveWidget extends Composite implements DictionarySaveDisplay {
	
	@UiField
	TextBox name;
	@UiField
	Button addBtn;
	@UiField
	Panel breadCrumbs;
	private static DictionarySaveWidgetUiBinder uiBinder = GWT
			.create(DictionarySaveWidgetUiBinder.class);

	interface DictionarySaveWidgetUiBinder extends UiBinder<Widget, DictionarySaveWidget> {
	}

	public DictionarySaveWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getAddBtnClickHandlers() {
		return addBtn;
	}

	@Override
	public TextBox getName() {
		return name;
	}
	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);

	}

}
