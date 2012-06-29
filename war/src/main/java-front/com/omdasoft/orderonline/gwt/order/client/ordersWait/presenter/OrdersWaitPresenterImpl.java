package com.omdasoft.orderonline.gwt.order.client.ordersWait.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.EltGinjector;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria.OrderStatus;
import com.omdasoft.orderonline.gwt.order.client.ordersWait.request.OrderWaitRequest;
import com.omdasoft.orderonline.gwt.order.client.ordersWait.request.OrderWaitResponse;
import com.omdasoft.orderonline.gwt.order.client.view.constant.CssStyleConstants;


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
	 Timer timer;
	 Timer timer2;
	@Override
	public void bind() {
		
		display.getTimeText().getElement().getParentElement().getParentElement().setClassName("totalAll lineStyle");
		display.getTitletext().getElement().getParentElement().getParentElement().setClassName("totalAll lineStyle");

		   display.getTimeText().setText("0");
		   display.getMessageText().setText("");

		if(roomfal==true)
		{
			display.getTitletext().setText("订餐已完成，正在等待线下接收。。。");

	
			 timer = new Timer() {  
			      public void run() {  
			 
			        display.getTimeText().setText((Integer.parseInt(display.getTimeText().getText())+1)+"");
			      }  
			    };  
			  
			    // Schedule the timer to run once every second  
			    timer.scheduleRepeating(1000);  
			    
			    
			   timer2 = new Timer() {  
				      public void run() {  
				    	  dispatch.execute(new OrderWaitRequest(injector.getOrderManager().getOrderRequest().getId()), new AsyncCallback<OrderWaitResponse>() {
								@Override
								public void onFailure(Throwable e) {
									errorHandler.alert(e.getMessage());
								}

								@Override
								public void onSuccess(OrderWaitResponse response) {
									if(response.getStatus()==OrderStatus.SUCCESS)
									{
										display.getMessageText().setText("订餐信息已被东北人珠海拱北店接收，当您前往餐厅消费时，请到营业台办理现场核对手续。");
										timer.cancel();
										timer2.cancel();
										display.getTimeText().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
										display.getTitletext().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
									}
									else if(response.getStatus()==OrderStatus.FAILURE)
									{
										display.getMessageText().setText("您的订房暂时未被接受，您可以稍后通过电话、网络再次尝试订房，也可以到现场侯位，然后下载使用您的菜单。");
										timer.cancel();
										timer2.cancel();
										display.getTimeText().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
										display.getTitletext().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
									}
									else if(response.getStatus()==null)
										timer2.cancel();

									
								}

							});
				      }
				      
				    };  
				  
				    // Schedule the timer to run once every second  
				    timer2.scheduleRepeating(5000);
		}
		else
		{
			display.getTimeText().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
			display.getTitletext().setText("您所点的菜品已经保存在网上，当您前往餐厅消费时，可到营业厅通过手机号下载使用您的菜单。");
		}
	}

	@Override
	public void setRoomFal(boolean roomfal) {
		this.roomfal=roomfal;
		
	}



}
