package com.omdasoft.orderonline.gwt.order.client.win.confirm;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.omdasoft.orderonline.gwt.order.client.mvp.DialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;

public interface ConfirmPresenter extends DialogPresenter<ConfirmPresenter.ConfirmDisplay> {

	public static interface ConfirmDisplay extends Display {
		HasClickHandlers getOkBtn();

		HasClickHandlers getCancelBtn();

		void setMsg(String msg);
	}

}
