package com.omdasoft.orderonline.gwt.order.client.dishesSave.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.presenter.DishesSavePresenter.DishesSaveDisplay;
import com.omdasoft.orderonline.gwt.order.client.view.constant.CssStyleConstants;

public class DishesSaveWidget extends Composite implements DishesSaveDisplay {

	@UiField
	Button addBtn;
	@UiField
	TextBox name;
	@UiField
	TextArea description;
	@UiField
	TextBox photo;
	@UiField
	Image giftImage;
	@UiField
	FormPanel photoForm;
	@UiField
	FileUpload photoUpload;
	@UiField
	Button photoUploadBtn;
	@UiField
	ListBox dishesType;
	@UiField
	TextBox price;
	
	@UiField
	RadioButton status0;
	@UiField
	RadioButton status1;
	@UiField
	RadioButton status2;
	@UiField
	Panel breadCrumbs;
	@UiField
	InlineLabel rid;
	@UiField
	Panel checkBoxPanel;
	
	private static DishesSaveWidgetUiBinder uiBinder = GWT
			.create(DishesSaveWidgetUiBinder.class);

	interface DishesSaveWidgetUiBinder extends UiBinder<Widget, DishesSaveWidget> {
	}

	public DishesSaveWidget() {
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
	public FormPanel getPhotoForm() {
		return photoForm;
	}

	@Override
	public FileUpload getPhotoUpload() {
		return photoUpload;
	}

	@Override
	public HasClickHandlers getUploadClick() {
		return photoUploadBtn;
	}

	@Override
	public HasValue<String> getPhoto() {
		return photo;
	}

	@Override
	public Image getGiftImage() {
		return giftImage;
	}

	@Override
	public TextArea getDescription() {
		return description;
	}

	@Override
	public ListBox getDishesType() {
		return dishesType;
	}

	@Override
	public TextBox getPrice() {
		return price;
	}

	@Override
	public int getStatus() {
		if(status0.getValue())
		return 0;
		if(status1.getValue())
		return 1;
		if(status2.getValue())
		return 2;
		else
		return -1;
	}

	@Override
	public void setStatus(int status) {
		if(status==0)
			status0.setValue(true);
		if(status==1)
			status1.setValue(true);
		if(status==2)
			status2.setValue(true);
		
		
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
	public void setAddBtnDisable(boolean fal) {
		addBtn.setEnabled(fal);
		
	}

	@Override
	public Panel getCheckBoxPanel() {
		return checkBoxPanel;
	}

	
}
