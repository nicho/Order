package com.omdasoft.orderonline.gwt.order.client.win.loginconfirm;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.mvp.BaseDialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.WinEvent;

public class LoginConfirmPresenterImpl extends
		BaseDialogPresenter<LoginConfirmPresenter.LoginConfirmDisplay> implements LoginConfirmPresenter {

	@Inject
	public LoginConfirmPresenterImpl(EventBus eventBus, LoginConfirmPresenter.LoginConfirmDisplay display) {
		super(eventBus, display);
	}

	@Override
	public void bind() {
		registerHandler(display.getOkBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new WinEvent());
//				closeDialog();
			}
		}));

	}
}
