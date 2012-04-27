package com.omdasoft.orderonline.gwt.order.client.dispatch;

import net.customware.gwt.dispatch.client.ExceptionHandler;
import net.customware.gwt.dispatch.shared.secure.InvalidSessionException;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.login.event.LoginEvent;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;

public class EltDispatchExceptionHandler implements ExceptionHandler {

	final EventBus eventBus;
	
	@Inject
	public EltDispatchExceptionHandler(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	@Override
	public Status onFailure(Throwable e) {
		if (e instanceof InvalidSessionException) {
			eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
			return Status.STOP;
		}
		return Status.CONTINUE;
	}

}
