package com.omdasoft.orderonline.gwt.order.client.password.dialog;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractDialog;
import com.omdasoft.orderonline.gwt.order.client.password.presenter.PasswordPresenter;

public class PasswordDialog extends AbstractDialog {

	final Provider<PasswordPresenter> presenterProvider;

	PasswordPresenter presenter;

	@Inject
	public PasswordDialog(
			Provider<PasswordPresenter> presenterProvider) {
		super("staff.passward", "staff.passward");
		this.presenterProvider = presenterProvider;
		presenter = presenterProvider.get();
		presenter.setDialog(this);

		init();
	}

	public void init() {
		setTitle("修改密码");

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


}
