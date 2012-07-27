package com.omdasoft.orderonline.gwt.order.client.ordersLogin.presenter;

import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.Elt;
import com.omdasoft.orderonline.gwt.order.client.EltGinjector;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.module.OrderManager;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.CityInitRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.CityInitResponse;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderInitRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderInitResponse;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.request.OrderLoginRequest;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.request.OrderLoginResponse;
import com.omdasoft.orderonline.gwt.order.client.view.constant.CssStyleConstants;
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
		
		Map<String, List<String>> maps = Window.Location.getParameterMap(); 

		if(maps.get("did")!=null)
			display.getCity().getElement().getParentElement().addClassName(CssStyleConstants.hidden);
		
		
		
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
		
		
		dispatch.execute(new OrderInitRequest(Elt.CORPORATIONID), new AsyncCallback<OrderInitResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(OrderInitResponse response) {
				if (response.getCityName() != null
						&& response.getCityName().size() > 0) {
					display.getCity().clear();
					int x=0;
					int i=0;
					for (String city : response.getCityName()) {
						display.getCity().addItem(city, city);
						if(injector.getOrderManager()!=null && injector.getOrderManager().getOrderRequest() !=null && injector.getOrderManager().getOrderRequest().getCity()!=null)
							if(city.equals(injector.getOrderManager().getOrderRequest().getCity()))
								x=i;
						i++;
					}
					display.getCity().setSelectedIndex(x);
					changeCity();
					

				}

			}

		});
		registerHandler(display.getCity().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent arg0) {

				changeCity();

			}
		}));
	}
	public void changeCity() {
		dispatch.execute(
				new CityInitRequest(Elt.CORPORATIONID, display.getCity().getValue(
						display.getCity().getSelectedIndex())),
				new AsyncCallback<CityInitResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(CityInitResponse response) {
						if (response.getDeptName() != null
								&& response.getDeptName().size() > 0) {
							display.getrestaurant().clear();
							for (String[] restaurant : response.getDeptName()) {
								display.getrestaurant().addItem(restaurant[1],restaurant[0]);
							}
							
							
							if(injector.getOrderManager()!=null && injector.getOrderManager().getOrderRequest() !=null && injector.getOrderManager().getOrderRequest().getRestaurantId()!=null 
									&& display.getCity().getValue(display.getCity().getSelectedIndex()).equals(injector.getOrderManager().getOrderRequest().getCity()) )
							{
								for(int j=0;j<display.getrestaurant().getItemCount();j++)
								{
									if(injector.getOrderManager().getOrderRequest().getRestaurantId().equals(display.getrestaurant().getValue(j)))
										display.getrestaurant().setSelectedIndex(j);
								}
								
							}
							
						}

					}

				});
	}
	void doLogin()
	{
		if(!StringUtil.isEmpty(display.getPhone().getValue()))
		{
			Map<String, List<String>> maps = Window.Location.getParameterMap(); 
			 String deptId="";
			if(maps.get("did")!=null)
				deptId=Elt.DEPARTMENTID;
			else
				deptId=display.getrestaurant().getValue(display.getrestaurant().getSelectedIndex());
			
			//查询订单信息，和点菜信息
			dispatch.execute(new OrderLoginRequest(display.getPhone().getValue(),deptId), new AsyncCallback<OrderLoginResponse>() {
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
						request.setAmountOfClient(0);
						request.setOrderPersonPhone(display.getPhone().getValue());
						request.setCity(response.getCity());
						request.setRestaurantName(response.getRestaurantName());
						request.setRestaurantId(response.getRestaurantId());
					}
					OrderManager xxx=injector.getOrderManager();
					if((xxx!=null && xxx.getOrderRequest()!=null && !xxx.getOrderRequest().getOrderPersonPhone().equals(display.getPhone().getValue())) || (xxx!=null && xxx.getOrderRequest()!=null && !xxx.getOrderRequest().getRestaurantId().equals(display.getrestaurant().getValue(display.getrestaurant().getSelectedIndex()))))
						injector.getOrdersDishesPresenter().cleanDishes();
					
					injector.getOrderManager().setOrderRequest(request);
				//	injector.getOrdersDishesPresenter().initOrdersDishes(request);
					
						String deptId="";
						Map<String, List<String>> maps = Window.Location.getParameterMap(); 
						if(maps.get("did")!=null)
							deptId=Elt.DEPARTMENTID;
						else
							deptId=display.getrestaurant().getValue(display.getrestaurant().getSelectedIndex());
						
					injector.getOrdersDishesPresenter().initDeptId(deptId);
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
