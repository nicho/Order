package com.omdasoft.orderonline.gwt.order.client.win.alert;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.omdasoft.orderonline.gwt.order.client.mvp.DialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;

public interface AlertPresenter extends
		DialogPresenter<AlertPresenter.AlertDisplay> {

	public static interface AlertDisplay extends Display {
		HasClickHandlers getOkBtn();

		void setMsg(String msg);
		void setImage(String url);
	}

}
