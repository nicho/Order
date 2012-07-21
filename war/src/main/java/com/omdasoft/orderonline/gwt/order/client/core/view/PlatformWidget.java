package com.omdasoft.orderonline.gwt.order.client.core.view;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.core.presenter.PlatformPresenter.PlatformDisplay;
import com.omdasoft.orderonline.gwt.order.client.core.view.constant.ViewConstants;

public class PlatformWidget extends Composite implements PlatformDisplay {

	@UiField
	DockLayoutPanel dock;

	@UiField 
	FlowPanel menu;

	@UiField
	Anchor logBtn;

	@UiField
	InlineLabel message;

	@UiField
	Anchor btnCompany;
	@UiField
	Anchor collectionBtn;

	@UiField
	InlineLabel menuTitle;

	@UiField
	Anchor platformManagement;

	
	// Set the format of datepicker.
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format_chinese);

	interface PlatformWidgetBinder extends UiBinder<Widget, PlatformWidget> {
	}

	private static PlatformWidgetBinder uiBinder = GWT
			.create(PlatformWidgetBinder.class);

	public PlatformWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getlogBtn() {
		return logBtn;
	}

	String styleOn = "";
	String styleNo = "";

	private void init() {
		styleOn = btnCompany.getStyleName();
		btnCompany.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnCompany.setStyleName(styleOn);
			}
		});
	}

	@Override
	public DockLayoutPanel getDock() {
		return dock;
	}

	@Override
	public Panel getMenu() {
		return menu;
	}

	@Override
	public void setMessage(String userName) {
		String time = dateFormat.format(new Date());
		String msg = "欢迎你，" + userName + "！今天是:" + time;
		message.setText(msg);
	}

	@Override
	public HasClickHandlers getBtnCompany() {
		return btnCompany;
	}

	@Override
	public void setMenu(Panel panel) {
		menu.clear();

	}

	@Override
	public void setMenuTitle(String title) {
		menuTitle.setText(title);
	}

	@Override
	public HasClickHandlers getBtnCollection() {
		return collectionBtn;
	}

	@Override
	public HasClickHandlers getPlatformManagement() {
		return platformManagement;
	}

	@Override
	public void changeTopMenu(String key) {
		if ("Company".equals(key)) {
			btnCompany.setStyleName(styleOn);
		}
	}



	
}
