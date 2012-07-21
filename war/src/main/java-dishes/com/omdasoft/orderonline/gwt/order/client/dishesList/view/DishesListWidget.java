package com.omdasoft.orderonline.gwt.order.client.dishesList.view;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.dishesList.presenter.DishesListPresenter.DishesListDisplay;
import com.omdasoft.orderonline.gwt.order.client.view.constant.CssStyleConstants;

public class DishesListWidget extends Composite implements DishesListDisplay {


	@UiField
	TextBox searchName;
	@UiField
	Button addBtn;
	@UiField
	Button copyBtn;
	
	@UiField
	Button searchBtn;
	
	@UiField
	InlineLabel dataCount;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	@UiField
	Panel breadCrumbs;
	@UiField
	ListBox pageNumber;
	@UiField
	Panel tabpage;
	@UiField
	Anchor typeall;
	private static DishesListWidgetUiBinder uiBinder = GWT
			.create(DishesListWidgetUiBinder.class);

	interface DishesListWidgetUiBinder extends
			UiBinder<Widget, DishesListWidget> {
	}

	public DishesListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
		pageNumber.clear();
		pageNumber.addItem("10","10");
		pageNumber.addItem("20","20");
		pageNumber.addItem("50","50");
		pageNumber.addItem("100","100");
	}

	@Override
	public ListBox getPageNumber() {
		return pageNumber;
	}
	
	@Override
	public HasClickHandlers getSearchBtnClickHandlers() {
		return this.searchBtn;
	}



	@Override
	public HasClickHandlers getAddBtnClickHandlers() {
		return this.addBtn;
	}




	@Override
	public void setDataCount(String text) {
		dataCount.setText(text);
		
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
		
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
	public HasValue<String> getSearchName() {
		return searchName;
	}


	@Override
	public Panel getTabpage() {
		return tabpage;
	}
	@Override
	public Anchor getTypeall() {
		return typeall;
	}

	@Override
	public HasClickHandlers getCopyBtnClickHandlers() {
		return copyBtn;
	}

	@Override
	public void hiddenCopyBtn() {
		copyBtn.getElement().getParentElement().setClassName(CssStyleConstants.hidden);
		
	}


}
