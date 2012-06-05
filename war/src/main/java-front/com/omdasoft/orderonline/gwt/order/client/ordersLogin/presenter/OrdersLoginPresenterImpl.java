package com.omdasoft.orderonline.gwt.order.client.ordersLogin.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;


public class OrdersLoginPresenterImpl extends
		BasePresenter<OrdersLoginPresenter.OrdersLoginDisplay> implements
		OrdersLoginPresenter {

	private final DispatchAsync dispatch;

	final ErrorHandler errorHandler;

	@Inject
	public OrdersLoginPresenterImpl(EventBus eventBus,
			OrdersLoginDisplay display, DispatchAsync dispatch,ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler=errorHandler;

	}

	@Override
	public void bind() {
		display.getSubmitBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(!StringUtil.isEmpty(display.getPhone().getValue()))
				{
					//查询订单信息，和点菜信息
					
				}
				else
				{
					Window.alert("请输入手机号码！");
					return;
				}
				
			}
		});
	}

	
	
}
