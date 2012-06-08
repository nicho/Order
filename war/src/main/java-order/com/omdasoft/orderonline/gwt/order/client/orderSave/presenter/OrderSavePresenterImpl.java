package com.omdasoft.orderonline.gwt.order.client.orderSave.presenter;

import java.util.Date;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria.OrderStatus;
import com.omdasoft.orderonline.gwt.order.client.orderList.plugin.OrderListConstants;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.CityInitRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.CityInitResponse;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.FindOrderRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.FindOrderResponse;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderInitRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderInitResponse;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveResponse;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class OrderSavePresenterImpl extends
		BasePresenter<OrderSavePresenter.OrderSaveDisplay> implements
		OrderSavePresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;

	private final BreadCrumbsPresenter breadCrumbs;
	String orderId = null;
	OrderSaveRequest request;
	@Inject
	public OrderSavePresenterImpl(EventBus eventBus, OrderSaveDisplay display,
			DispatchAsync dispatch, SessionManager sessionManager, Win win,
			BreadCrumbsPresenter breadCrumbs, ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		if (orderId == null) {
			display.hiddenStatus();
			breadCrumbs.loadChildPage("添加订单");
		} else {
			breadCrumbs.loadChildPage("修改订单");
			display.setTitleText("修改订单");
			FindOrderRequest findrequest = new FindOrderRequest();
			findrequest.setOrderId(orderId);
			dispatch.execute(findrequest,
					new AsyncCallback<FindOrderResponse>() {
						@Override
						public void onFailure(Throwable e) {
							errorHandler.alert(e.getMessage());
						}

						@Override
						public void onSuccess(FindOrderResponse response) {
							initOrderRequest(response);
						}

					});
		}
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
	}

	public void initOrderRequest(FindOrderResponse request) {

		if(!StringUtil.isEmpty(request.getAmountOfClient()+"") && request.getAmountOfClient()!=0)
		{
			display.getAmountOfClient().setValue(request.getAmountOfClient()+"");
		}
		if(!StringUtil.isEmpty(request.getCity()))
		{
			for (int i = 0; i < display.getCity().getItemCount(); i++) {
				if(display.getCity().getValue(i).equals(request.getCity()))
				{
					display.getCity().setSelectedIndex(i);
					break;
				}
			}
		}
		if(!StringUtil.isEmpty(request.getContactPersonName()))
		{
			display.getContactPersonName().setValue(request.getContactPersonName());
			display.getOtherPersonCheckbox().setValue(true);
		}
		if(!StringUtil.isEmpty(request.getContactPersonPhone()))
			display.getContactPersonPhone().setValue(request.getContactPersonPhone());
		if(!StringUtil.isEmpty(request.getFavoriteRoom()+"") && request.getFavoriteRoom()!=0)
		{
			if (request.getFavoriteRoom()== 1)
				display.getFavoriteRoom1().setValue(true);
			else if (request.getFavoriteRoom()== 2)
				display.getFavoriteRoom2().setValue(true);
			else if (request.getFavoriteRoom()== 3)
				display.getFavoriteRoom3().setValue(true);
			else if (request.getFavoriteRoom()== 4)
				display.getFavoriteRoom4().setValue(true);
		}
		if(!StringUtil.isEmpty(request.getMemo()))
			display.getMemo().setValue(request.getMemo());
		if(!StringUtil.isEmpty(request.getOrderPersonName()))
			display.getOrderPersonName().setValue(request.getOrderPersonName());
		if(!StringUtil.isEmpty(request.getOrderPersonPhone()))
			display.getOrderPersonPhone().setValue(request.getOrderPersonPhone());
		if(!StringUtil.isEmpty(request.getOrderPersonSex()))
		{
			if ("男".equals(request.getOrderPersonSex()))
				display.getSex1().setValue(true);
			else if ("女".equals(request.getOrderPersonSex()))
				display.getSex2().setValue(true);
		}
		if(!StringUtil.isEmpty(request.getContactPersonSex()))
		{
			if ("男".equals(request.getContactPersonSex()))
				display.getSex3().setValue(true);
			else if ("女".equals(request.getContactPersonSex()))
				display.getSex4().setValue(true);
		}
		
		if(!StringUtil.isEmpty(request.getReserveTimeDate()))
		{
			for (int i = 0; i < display.getReserveTime().getItemCount(); i++) {
				if(display.getReserveTime().getValue(i).equals(request.getReserveTimeDate()))
				{
					display.getReserveTime().setSelectedIndex(i);
					break;
				}
			}
		}
		if(!StringUtil.isEmpty(request.getReserveTimeDateH()))
		{
			for (int i = 0; i < display.getReserveTimeH().getItemCount(); i++) {
				if(display.getReserveTimeH().getValue(i).equals(request.getReserveTimeDateH()))
				{
					display.getReserveTimeH().setSelectedIndex(i);
					break;
				}
			}
		}
		if(!StringUtil.isEmpty(request.getReserveTimeDateS()))
		{
			for (int i = 0; i < display.getReserveTimeS().getItemCount(); i++) {
				if(display.getReserveTimeS().getValue(i).equals(request.getReserveTimeDateS()))
				{
					display.getReserveTimeS().setSelectedIndex(i);
					break;
				}
			}
		}
		if(!StringUtil.isEmpty(request.getRestaurantId()))
		{
			for (int i = 0; i < display.getrestaurant().getItemCount(); i++) {
				if(display.getrestaurant().getValue(i).equals(request.getRestaurantId()))
				{
					display.getrestaurant().setSelectedIndex(i);
					break;
				}
			}
		}
		display.initStatus(request.getStatus());
	}
	private void init() {
		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						createRequest();
						
						if(verificationRequest(request))
						{
							if(!StringUtil.isEmpty(orderId))
								request.setId(orderId);
							request.setDishesOrRoomFal("ROOM");
						dispatch.execute(request,
								new AsyncCallback<OrderSaveResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(
											OrderSaveResponse response) {
										win.alert("保存成功!");
										Platform.getInstance()
												.getEditorRegistry()
												.openEditor(
														OrderListConstants.EDITOR_ORDERLIST_SEARCH,
														"EDITOR_ORDERLIST_SEARCH_DO_ID",
														null);
									}

								});
						}
					}
				}));

		
		dispatch.execute(new OrderInitRequest(sessionManager.getSession()
				.getCorporationId()),
				new AsyncCallback<OrderInitResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(OrderInitResponse response) {
						if (response.getCityName() != null
								&& response.getCityName().size() > 0) {
							display.getCity().clear();
							for (String city : response.getCityName()) {
								display.getCity().addItem(city,city);
							}
							changeCity();
						}

					}

				});
		display.getCity().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				
				changeCity();
				
			}
		});
	}

	public void changeCity()
	{
		dispatch.execute(new CityInitRequest(sessionManager.getSession()
				.getCorporationId(),display.getCity().getValue(display.getCity().getSelectedIndex())),
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
						}

					}

				});
	}
	@Override
	public void initOrder(String orderId) {
		this.orderId = orderId;
	}
	private void createRequest()
	{
		if(request==null)
			request = new OrderSaveRequest();
		 
		if (!StringUtil.isEmpty(display.getAmountOfClient().getValue())) 
			request.setAmountOfClient(Integer.parseInt(display.getAmountOfClient().getValue()));
		if (display.getCity().getSelectedIndex() >= 0)
			request.setCity(display.getCity().getValue(display.getCity().getSelectedIndex()));
		
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
		if (display.getrestaurant().getSelectedIndex() >= 0)
			request.setRestaurantId(display.getrestaurant()
					.getValue(
							display.getrestaurant()
									.getSelectedIndex()));
		if (orderId == null) 
			request.setOrderStatus(OrderStatus.UNHANDLED);
		else
			request.setOrderStatus(OrderStatus.valueOf(display.getStatus()));
	}
	private boolean verificationRequest(OrderSaveRequest req)
	{
		if (StringUtil.isEmpty(req.getAmountOfClient()+"") || req.getAmountOfClient()==0) {
			Window.alert("请填写就餐人数");
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
		if (StringUtil.isEmpty(req.getFavoriteRoom()+"") || req.getFavoriteRoom()==0) {
			Window.alert("请选择预定类型");
			return false;
		}
		return true;
	}
}
