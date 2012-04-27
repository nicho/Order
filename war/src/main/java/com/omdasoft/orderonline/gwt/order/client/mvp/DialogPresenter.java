package com.omdasoft.orderonline.gwt.order.client.mvp;

import com.omdasoft.orderonline.gwt.order.client.core.ui.Dialog;

public interface DialogPresenter<D extends Display> extends Presenter<D> {

	/**
	 * Set me if we use this presenter as a dialog
	 * 
	 * @param parent
	 */
	void setDialog(Dialog parent);

}