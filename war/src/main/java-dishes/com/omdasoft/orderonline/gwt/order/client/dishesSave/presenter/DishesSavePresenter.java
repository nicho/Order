package com.omdasoft.orderonline.gwt.order.client.dishesSave.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface DishesSavePresenter extends Presenter<DishesSavePresenter.DishesSaveDisplay> {
	
public void initDishes(String orderId);
	public static interface DishesSaveDisplay extends Display {

		public HasClickHandlers getAddBtnClickHandlers();
		TextBox getName();
		public FormPanel getPhotoForm();

		public FileUpload getPhotoUpload();

		public HasClickHandlers getUploadClick();
		public HasValue<String> getPhoto();

		public Image getGiftImage();
		TextArea getDescription();
		ListBox getDishesType();
		TextBox getPrice();
		int getStatus();
		void setStatus(int status);
		void setBreadCrumbs(Widget breadCrumbs);
		void setRid(String text);
		void hiddenRid();
		void setAddBtnDisable(boolean fal);
		Panel getCheckBoxPanel();
	}
}
