package com.omdasoft.orderonline.gwt.order.client.ordersLogin.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
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
		registerHandler(display.getSubmitBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				doLogin();
			}
		}));
		
		registerHandler(display.getPhone().addKeyUpHandler(
				new KeyUpHandler() {
					@Override
					public void onKeyUp(KeyUpEvent e) {
						if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
							doLogin();
						}
					}
				}));
	}

	void doLogin()
	{
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
						request.setRestaurantId(response.getRestaurantId());
						request.setMemo(response.getMemo());
						request.setFavoriteRoom(response.getFavoriteRoom());
						
						if(injector.getOrderManager()==null || injector.getOrderManager().getOrderRequest()==null || injector.getOrderManager().getOrderRequest().getBookingDishesListClient()==null)
							request.setBookingDishesListClient(response.getBookingDishesList());

					}
					else
					{
						request.setOrderPersonPhone(display.getPhone().getValue());
					}
					injector.getOrderManager().setOrderRequest(request);
				//	injector.getOrdersDishesPresenter().initOrdersDishes(request);
					
					injector.getOrderIndexPresenter().initPresenter(injector.getOrdersDishesPresenter());
				
					
				
					
				}

			});
		}
		else
		{
			Window.alert("请输入手机号码！");
			return;
		}
	}

	@Override
	public void rsDoLogin() {
		if(!StringUtil.isEmpty(display.getPhone().getValue()))
		doLogin();
	}
	
}
