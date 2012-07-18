package com.omdasoft.orderonline.gwt.order.client.orderSubmit.presenter;

import java.util.Date;

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
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria.OrderStatus;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveResponse;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.view.constant.CssStyleConstants;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class OrderSubmitPresenterImpl extends
		BasePresenter<OrderSubmitPresenter.OrderSubmitDisplay> implements
		OrderSubmitPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	// private final Win win;
	final ErrorHandler errorHandler;
	private final EltGinjector injector;

	
	OrderSaveRequest request;
	String onCss=display.getContactPersonName().getElement().getParentElement().getParentElement().getParentElement().getClassName();
	
	@Inject
	public OrderSubmitPresenterImpl(EventBus eventBus,
			OrderSubmitDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager,
			ErrorHandler errorHandler,EltGinjector injector) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
		// this.win = win;
		this.injector = injector;
	}

	@Override
	public void bind() {
	
		init();
	}

	private void init() {
		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
	
						createRequest();
	
						if(verificationRequest(request))
						{

							dispatch.execute(request,
									new AsyncCallback<OrderSaveResponse>() {
										@Override
										public void onFailure(Throwable e) {
											errorHandler.alert(e.getMessage());
										}
	
										@Override
										public void onSuccess(
												OrderSaveResponse response) {
											injector.getOrdersWaitPresenter().setRoomFal(true);
											injector.getOrderIndexPresenter().initPresenter(injector.getOrdersWaitPresenter());
		
										}
	
									});
						}
					}
				}));
	

	
		display.getContactPersonName().getElement().getParentElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
		registerHandler(display.getOtherPersonCheckbox().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				if(!display.getOtherPersonCheckbox().getValue())
				{
					display.getContactPersonName().getElement().getParentElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
				}
				else
				{
					display.getContactPersonName().getElement().getParentElement().getParentElement().getParentElement().setClassName(onCss);
				}
				
			}
		}));
		
		
		if(injector.getOrderManager().getOrderRequest()!=null)
		{
			display.getCity().setText(injector.getOrderManager().getOrderRequest().getCity());
			display.getrestaurant().setText(injector.getOrderManager().getOrderRequest().getRestaurantName());
		}

	}
	
	


	private void createRequest()
	{
		if(request==null)
			request = new OrderSaveRequest();
		 
		if (!StringUtil.isEmpty(display.getAmountOfClient().getValue())) 
			request.setAmountOfClient(Integer.parseInt(display.getAmountOfClient().getValue()));
		
			request.setCity(injector.getOrderManager().getOrderRequest().getCity());
		
		request.setContactPersonName(display.getContactPersonName().getValue());
		request.setContactPersonPhone(display.getContactPersonPhone().getValue());
		if(display.getSex3().getValue())
		request.setContactPersonSex("男");
		else if(display.getSex4().getValue())
		request.setContactPersonSex("女");
		
		request.setCorporationId(sessionManager.getSession().getCorporationId());
		
		if (display.getFavoriteRoom1().getValue() == true)
			request.setFavoriteRoom(1);
		if (display.getFavoriteRoom2().getValue() == true)
			request.setFavoriteRoom(2);
		if (display.getFavoriteRoom3().getValue() == true)
			request.setFavoriteRoom(3);
		if (display.getFavoriteRoom4().getValue() == true)
			request.setFavoriteRoom(4);
		request.setMemo(display.getMemo().getValue());
		request.setOrderPersonName(display.getOrderPersonName().getValue());
		request.setOrderPersonPhone(display.getOrderPersonPhone().getValue());
		if(display.getSex1().getValue())
		request.setOrderPersonSex("男");
		else if(display.getSex2().getValue())
		request.setOrderPersonSex("女");
		request.setPlaceOrderTime(new Date());
		
		if (display.getReserveTime().getSelectedIndex() >= 0)
			request.setReserveTimeDate(display.getReserveTime()
					.getValue(
							display.getReserveTime()
									.getSelectedIndex()));
		if (display.getReserveTimeH().getSelectedIndex() >= 0)
			request.setReserveTimeDateH(display
					.getReserveTimeH().getValue(
							display.getReserveTimeH()
									.getSelectedIndex()));
		if (display.getReserveTimeS().getSelectedIndex() >= 0)
			request.setReserveTimeDateS(display
					.getReserveTimeS().getValue(
							display.getReserveTimeS()
									.getSelectedIndex()));

			request.setRestaurantId(injector.getOrderManager().getOrderRequest().getRestaurantId());
			request.setRestaurantName(injector.getOrderManager().getOrderRequest().getRestaurantName());

	
		request.setOrderStatus(OrderStatus.UNHANDLED);
		
		if(!StringUtil.isEmpty(injector.getOrderManager().getOrderRequest().getId()))
		{
			request.setId(injector.getOrderManager().getOrderRequest().getId());
		}
		request.setDishesOrRoomFal("ROOM");
	}
	
	private boolean verificationRequest(OrderSaveRequest req)
	{
		if (StringUtil.isEmpty(req.getAmountOfClient()+"") || req.getAmountOfClient()==0) {
			Window.alert("请填写就餐人数");
			return false;
		}
		if (StringUtil.isEmpty(req.getFavoriteRoom()+"") || req.getFavoriteRoom()==0) {
			Window.alert("请选择预定房间类型");
			return false;
		}
		if (StringUtil.isEmpty(req.getOrderPersonName())) {
			Window.alert("请填写姓名");
			return false;
		}
		if (StringUtil.isEmpty(req.getOrderPersonPhone())) {
			Window.alert("请填写手机号");
			return false;
		}

		return true;
	}

	@Override
	public void initOrderPhone(String phone) {
		display.getOrderPersonPhone().setValue(phone);
		
	}

//	@Override
//	public void initOrderRequest(OrderSaveRequest request) {
//		this.request=request;
//
//		if(!StringUtil.isEmpty(request.getAmountOfClient()+"") && request.getAmountOfClient()!=0)
//		{
//			display.getAmountOfClient().setValue(request.getAmountOfClient()+"");
//		}
//		if(!StringUtil.isEmpty(request.getCity()))
//		{
//			for (int i = 0; i < display.getCity().getItemCount(); i++) {
//				if(display.getCity().getValue(i).equals(request.getCity()))
//				{
//					display.getCity().setSelectedIndex(i);
//					break;
//				}
//			}
//		}
//		if(!StringUtil.isEmpty(request.getContactPersonName()))
//			display.getContactPersonName().setValue(request.getContactPersonName());
//		if(!StringUtil.isEmpty(request.getContactPersonPhone()))
//			display.getContactPersonPhone().setValue(request.getContactPersonPhone());
//		if(!StringUtil.isEmpty(request.getFavoriteRoom()+"") && request.getFavoriteRoom()!=0)
//		{
//			if (request.getFavoriteRoom()== 1)
//				display.getFavoriteRoom1().setValue(true);
//			else if (request.getFavoriteRoom()== 2)
//				display.getFavoriteRoom2().setValue(true);
//			else if (request.getFavoriteRoom()== 3)
//				display.getFavoriteRoom3().setValue(true);
//			else if (request.getFavoriteRoom()== 4)
//				display.getFavoriteRoom4().setValue(true);
//		}
//		if(!StringUtil.isEmpty(request.getMemo()))
//			display.getMemo().setValue(request.getMemo());
//		if(!StringUtil.isEmpty(request.getOrderPersonName()))
//			display.getOrderPersonName().setValue(request.getOrderPersonName());
//		if(!StringUtil.isEmpty(request.getOrderPersonPhone()))
//			display.getOrderPersonPhone().setValue(request.getOrderPersonPhone());
//		
//		if(!StringUtil.isEmpty(request.getOrderPersonSex()))
//		{
//			if ("男".equals(request.getOrderPersonSex()))
//				display.getSex1().setValue(true);
//			else if ("女".equals(request.getOrderPersonSex()))
//				display.getSex2().setValue(true);
//		}
//		if(!StringUtil.isEmpty(request.getContactPersonSex()))
//		{
//			if ("男".equals(request.getContactPersonSex()))
//				display.getSex3().setValue(true);
//			else if ("女".equals(request.getContactPersonSex()))
//				display.getSex4().setValue(true);
//		}
//		
//		if(!StringUtil.isEmpty(request.getReserveTimeDate()))
//		{
//			for (int i = 0; i < display.getReserveTime().getItemCount(); i++) {
//				if(display.getReserveTime().getValue(i).equals(request.getReserveTimeDate()))
//				{
//					display.getReserveTime().setSelectedIndex(i);
//					break;
//				}
//			}
//		}
//		if(!StringUtil.isEmpty(request.getReserveTimeDateH()))
//		{
//			for (int i = 0; i < display.getReserveTimeH().getItemCount(); i++) {
//				if(display.getReserveTimeH().getValue(i).equals(request.getReserveTimeDateH()))
//				{
//					display.getReserveTimeH().setSelectedIndex(i);
//					break;
//				}
//			}
//		}
//		if(!StringUtil.isEmpty(request.getReserveTimeDateS()))
//		{
//			for (int i = 0; i < display.getReserveTimeS().getItemCount(); i++) {
//				if(display.getReserveTimeS().getValue(i).equals(request.getReserveTimeDateS()))
//				{
//					display.getReserveTimeS().setSelectedIndex(i);
//					break;
//				}
//			}
//		}
//		if(!StringUtil.isEmpty(request.getRestaurantId()))
//		{
//			for (int i = 0; i < display.getrestaurant().getItemCount(); i++) {
//				if(display.getrestaurant().getValue(i).equals(request.getRestaurantId()))
//				{
//					display.getrestaurant().setSelectedIndex(i);
//					break;
//				}
//			}
//		}
//		
//	}
}
