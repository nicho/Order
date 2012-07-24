package com.omdasoft.orderonline.gwt.order.client.login.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginEvent;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginEvent.LoginStatus;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginHandler;
import com.omdasoft.orderonline.gwt.order.client.login.presenter.LoginPresenter.LoginDisplay;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;

public class LoginPresenterImpl extends BasePresenter<LoginDisplay> implements
		LoginPresenter {

	final SessionManager sessionManager;
	@Inject
	public LoginPresenterImpl(EventBus eventBus, LoginDisplay display,
			SessionManager sessionManager) {
		super(eventBus, display);
		this.sessionManager = sessionManager;
	}

	@Override
	public void bind() {
		registerHandler(display.getLoginClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						doLogin();
					}
				}));
		registerHandler(eventBus.addHandler(LoginEvent.getType(),
				new LoginHandler() {
					@Override
					public void onLogin(LoginEvent event) {
						if (event.getStatus() == LoginStatus.LOGIN_FAILED) {
							display.changeImage();
						}
					}
				}));
		registerHandler(display.getPasswordBox().addKeyUpHandler(
				new KeyUpHandler() {
					@Override
					public void onKeyUp(KeyUpEvent e) {
						if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
							doLogin();
						}
					}
				}));


	}

	protected void doLogin() {
		sessionManager.authenticate(display.getUsername().getValue(), display
				.getPassword().getValue(),display.getVerifyCode().getValue(),display);
	}

}
