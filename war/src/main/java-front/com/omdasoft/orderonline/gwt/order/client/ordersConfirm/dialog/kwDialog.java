package com.omdasoft.orderonline.gwt.order.client.ordersConfirm.dialog;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractDialog;
import com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter.KwPresenter;

public class kwDialog extends AbstractDialog {

	final Provider<KwPresenter> presenterProvider;

	KwPresenter presenter;

	@Inject
	public kwDialog(
			Provider<KwPresenter> presenterProvider) {
		super("dishes.Kw", "dishes.Kw");
		this.presenterProvider = presenterProvider;
		presenter = presenterProvider.get();
		presenter.setDialog(this);

		init();
	}

	public void init() {
		setTitle("口味");

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
	public void initKW(List<String> kwlt,String value)
	{
		presenter.initKw(kwlt,value);	
	}
	public KwPresenter getPresenter()
	{
		return presenter;
	}
	public  List<String> getkwlt()
	{
		return presenter.getkwlt();
	}
}
