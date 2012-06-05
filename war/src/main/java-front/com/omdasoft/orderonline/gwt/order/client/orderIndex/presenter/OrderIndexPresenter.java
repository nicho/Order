package com.omdasoft.orderonline.gwt.order.client.orderIndex.presenter;


import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface OrderIndexPresenter extends Presenter<OrderIndexPresenter.OrderIndexDisplay> {
	
public void initPresenter(Widget widget);
	public static interface OrderIndexDisplay extends Display {

		Panel getDock();
		Anchor getAdminpage();
		Anchor getOrderListpage();
		Anchor getOrderNow();
	}
}
