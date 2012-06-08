package com.omdasoft.orderonline.gwt.order.client.ordersWait.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.user.client.Timer;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.EltGinjector;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;


public class OrdersWaitPresenterImpl extends
		BasePresenter<OrdersWaitPresenter.OrdersWaitDisplay> implements
		OrdersWaitPresenter {

	private final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	private final EltGinjector injector;
	boolean roomfal=false;
	@Inject
	public OrdersWaitPresenterImpl(EventBus eventBus,
			OrdersWaitDisplay display, DispatchAsync dispatch,ErrorHandler errorHandler,EltGinjector injector) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler=errorHandler;
		this.injector=injector;
	}

	@Override
	public void bind() {
		if(roomfal==true)
		{
			display.getTitletext().setText("订餐已完成，正在等待线下接收。。。");

	
			 Timer timer = new Timer() {  
			      public void run() {  
			 
			        display.getTimeText().setText((Integer.parseInt(display.getTimeText().getText())+1)+"");
			      }  
			    };  
			  
			    // Schedule the timer to run once every second  
			    timer.scheduleRepeating(1000);  
		}
		else
		{
			display.getTitletext().setText("您所点的菜品已经保存在网上，当您前往餐厅消费时，可到营业厅通过手机号下载使用您的菜单。");
		}
	}

	@Override
	public void setRoomFal(boolean roomfal) {
		this.roomfal=roomfal;
		
	}



}
