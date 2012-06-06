package com.omdasoft.orderonline.gwt.order.client.ordersLogin.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.EltGinjector;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.request.OrderLoginRequest;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.request.OrderLoginResponse;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;


public class OrdersLoginPresenterImpl extends
		BasePresenter<OrdersLoginPresenter.OrdersLoginDisplay> implements
		OrdersLoginPresenter {

	private final DispatchAsync dispatch;

	final ErrorHandler errorHandler;
	private final EltGinjector injector;
	@Inject
	public OrdersLoginPresenterImpl(EventBus eventBus,
			OrdersLoginDisplay display, DispatchAsync dispatch,ErrorHandler errorHandler,EltGinjector injector) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler=errorHandler;
		this.injector=injector;

	}

	@Override
	public void bind() {
		display.getSubmitBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(!StringUtil.isEmpty(display.getPhone().getValue()))
				{
					//查询订单信息，和点菜信息
					dispatch.execute(new OrderLoginRequest(display.getPhone().getValue()), new AsyncCallback<OrderLoginResponse>() {
						@Override
						public void onFailure(Throwable e) {
							errorHandler.alert(e.getMessage());
						}

						@Override
						public void onSuccess(OrderLoginResponse response) {
					
							
							
							OrderSaveRequest request=new OrderSaveRequest();
							if(response.getOrderId()!=null)
							{
								
								request.setAmountOfClient(response.getAmountOfClient());
								request.setCity(response.getCity());
								request.setOrderPersonName(response.getOrderPersonName());
								request.setOrderPersonPhone(response.getOrderPersonPhone());
								request.setId(response.getOrderId());
								request.setRestaurantName(response.getRestaurantName());
								request.setMemo(response.getMemo());
								request.setFavoriteRoom(response.getFavoriteRoom());
								

								request.setBookingDishesListClient(response.getBookingDishesList());

							}
							else
							{
								request.setOrderPersonPhone(display.getPhone().getValue());
							}
							injector.getOrderManager().setOrderRequest(request);
						//	injector.getOrdersDishesPresenter().initOrdersDishes(request);
							
							injector.getOrderIndexPresenter().initPresenter(injector.getOrdersDishesPresenter().getDisplay().asWidget());
						
							injector.getOrdersDishesPresenter().bind();
							
						
							
						}

					});
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
