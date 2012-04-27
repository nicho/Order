package com.omdasoft.orderonline.gwt.order.client.mvp;

import com.omdasoft.orderonline.gwt.order.client.core.ui.Dialog;

public abstract class BaseDialogPresenter<D extends Display> extends
		BasePresenter<D> implements DialogPresenter<D> {

	public BaseDialogPresenter(EventBus eventBus, D display) {
		super(eventBus, display);
	}

	Dialog parent = null;

	@Override
	public void setDialog(Dialog parent) {
		this.parent = parent;
	}

	protected void closeDialog() {
		if (parent != null) {
			parent.close();
		}
	}
}
