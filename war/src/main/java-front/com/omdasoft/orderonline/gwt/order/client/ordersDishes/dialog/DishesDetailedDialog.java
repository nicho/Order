package com.omdasoft.orderonline.gwt.order.client.ordersDishes.dialog;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractDialog;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.DishesDetailedPresenter;

public class DishesDetailedDialog extends AbstractDialog {

	final Provider<DishesDetailedPresenter> presenterProvider;

	DishesDetailedPresenter presenter;

	@Inject
	public DishesDetailedDialog(
			Provider<DishesDetailedPresenter> presenterProvider) {
		super("dishes.DishesDetailed", "dishes.DishesDetailed");
		this.presenterProvider = presenterProvider;
		presenter = presenterProvider.get();
		presenter.setDialog(this);

		init();
	}

	public void init() {
		setTitle("菜品详细");

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
	public void initDishesDetailed(String name,String prices,String description,String photo)
	{
		presenter.initDishesDetail(name, prices, description, photo);
	}

}
