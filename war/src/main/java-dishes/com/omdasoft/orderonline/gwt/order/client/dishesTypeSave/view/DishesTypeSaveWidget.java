package com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.presenter.DishesTypeSavePresenter.DishesTypeSaveDisplay;
import com.omdasoft.orderonline.gwt.order.client.view.constant.CssStyleConstants;

public class DishesTypeSaveWidget extends Composite implements DishesTypeSaveDisplay {
	
	@UiField
	Button addBtn;
	@UiField
	TextBox name;
	@UiField
	TextBox dishestype;
	
	@UiField
	Panel breadCrumbs;
	@UiField
	InlineLabel rid;
	@UiField
	Panel dishestypePanel;
	
	private static DishesTypeSaveWidgetUiBinder uiBinder = GWT
			.create(DishesTypeSaveWidgetUiBinder.class);

	interface DishesTypeSaveWidgetUiBinder extends UiBinder<Widget, DishesTypeSaveWidget> {
	}

	public DishesTypeSaveWidget() {
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
	@Override
	public void setRid(String text) {
		rid.setText(text);
	}
	@Override
	public void hiddenRid() {
		rid.getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
	}

	@Override
	public TextBox getDishestype() {
		return dishestype;
	}

	@Override
	public Panel getDishestypePanel() {
		return dishestypePanel;
	}

}
