package com.omdasoft.orderonline.gwt.order.client.win.loginconfirm;


import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractDialog;

public class LoginConfirmDialog extends AbstractDialog {
	final Provider<LoginConfirmPresenter> presenterProvider;
	LoginConfirmPresenter presenter;

	@Inject
	public LoginConfirmDialog(Provider<LoginConfirmPresenter> presenterProvider) {
		super("loginconfirm", "loginconfirm");
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

	public String getLoginType()
	{
		return presenter.getDisplay().getLoginType();
	}
}
