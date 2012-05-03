package com.omdasoft.orderonline.gwt.order.client.password.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.mvp.BaseDialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.password.request.PasswordRequest;
import com.omdasoft.orderonline.gwt.order.client.password.request.PasswordResponse;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class PasswordPresenterImpl extends	BaseDialogPresenter<PasswordPresenter.PasswordDisplay> implements	PasswordPresenter {

	final DispatchAsync dispatcher;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;


	@Inject
	public PasswordPresenterImpl(EventBus eventBus,ErrorHandler errorHandler, SessionManager sessionManager,
			PasswordDisplay display, DispatchAsync dispatcher,Win win	) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
	}

	@Override
	public void bind() {
		display.setUsername(sessionManager.getSession().getLoginName());
		registerHandler(display.getPasswordClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						if (StringUtil.isEmpty(display.getOldPassword().getValue())) {
							win.alert("旧密码不能为空!<br>");
							return;
						}						
						if (StringUtil.isEmpty(display.getNewPassword().getValue())) {
							win.alert("新密码不能为空!<br>");
							return;
						}
						if (display.getNewPassword().getValue().equals(display.getOldPassword().getValue())) {
							win.alert("新密码和旧密码不能相同!<br>");
							return;
						}
						if (!display.getNewPassword().getValue().equals(display.getValidatePassword().getValue())) {
							win.alert("新密码和确认密码不一致!<br>");
							return;
						}
						
						doPassword();
					}
				}));
	}

	protected void doPassword() {
	
		if(sessionManager.getSession()!=null){
		dispatcher.execute(new PasswordRequest(sessionManager.getSession().getToken(),display.getOldPassword().getValue(),display.getNewPassword().getValue(),sessionManager.getSession()),
				new AsyncCallback<PasswordResponse>() {
					public void onFailure(Throwable t) {
						win.alert("修改失败");
					}

					@Override
					public void onSuccess(PasswordResponse response) {
                        if(response.getMessage().equals("success"))
                        {
						   win.alert("修改成功!");
						   closeDialog();
                        }
                        else
                        	win.alert("修改失败,旧密码不对");
					}
				});
	    }else{
		win.alert("请重新登录!");
	    }
		
	}
	
}
