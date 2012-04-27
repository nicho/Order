package com.omdasoft.orderonline.gwt.order.client.win.alert;


import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractDialog;

public class AlertDialog extends AbstractDialog {
	final Provider<AlertPresenter> presenterProvider;
	AlertPresenter presenter;

	@Inject
	public AlertDialog(Provider<AlertPresenter> presenterProvider) {
		super("Alert", "Alert");
		this.presenterProvider = presenterProvider;
		presenter = presenterProvider.get();
		presenter.setDialog(this);
		presenter.bind();
		setTitle("消息");
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
	public void setImage(String url) {
		presenter.getDisplay().setImage(url);
	}
}
