package com.omdasoft.orderonline.gwt.order.client.ordersWait.presenter;


import com.google.gwt.user.client.ui.InlineLabel;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface OrdersWaitPresenter extends Presenter<OrdersWaitPresenter.OrdersWaitDisplay> {
	public void setRoomFal(boolean roomfal);
	public static interface OrdersWaitDisplay extends Display {
	
		InlineLabel getTitletext();
		InlineLabel getTimeText();
		InlineLabel getMessageText();
	}
}
