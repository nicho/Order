package com.omdasoft.orderonline.gwt.order.client.win.confirm;


import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractDialog;

public class ConfirmDialog extends AbstractDialog {
	final Provider<ConfirmPresenter> presenterProvider;
	ConfirmPresenter presenter;

	@Inject
	public ConfirmDialog(Provider<ConfirmPresenter> presenterProvider) {
		super("confirm", "confirm");
		this.presenterProvider = presenterProvider;
		presenter = presenterProvider.get();
		presenter.setDialog(this);
		presenter.bind();
	}

	@Override
	public Widget asWidget() {
		return presenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		presenter.unbind();
		return true;
	}

	public void setMsg(String msg) {
		presenter.getDisplay().setMsg(msg);
	}
}
