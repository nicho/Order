package com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter;

import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.omdasoft.orderonline.gwt.order.client.mvp.DialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;

public interface KwPresenter extends
		DialogPresenter<KwPresenter.KwDisplay> {
	public void initKw(List<String> kwlt,String value);
	public List<String> getkwlt();
	public static interface KwDisplay extends Display {
		
		Panel getCheckBoxPanel();
		Button getButtonSelect();
	}
	
}
