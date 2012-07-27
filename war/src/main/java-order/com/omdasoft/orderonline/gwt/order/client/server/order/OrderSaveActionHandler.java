package com.omdasoft.orderonline.gwt.order.client.server.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.order.Orders;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesList;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;
import com.omdasoft.orderonline.model.order.OrderStatus;
import com.omdasoft.orderonline.model.order.RoomState;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.vo.OrderDishesVo;
import com.omdasoft.orderonline.model.vo.OrderVo;
import com.omdasoft.orderonline.service.order.OrderService;

public class OrderSaveActionHandler extends
		BaseActionHandler<OrderSaveRequest, OrderSaveResponse> {

	OrderService orderService;
	
	@Inject
	public OrderSaveActionHandler(OrderService orderService)
	{
		this.orderService=orderService;
	}
	public OrderSaveActionHandler() {

	}
	@Override
	public Class<OrderSaveRequest> getActionType() {
		return OrderSaveRequest.class;
	}

	@Override
	public OrderSaveResponse execute(OrderSaveRequest action, ExecutionContext context)
			throws DispatchException {
		OrderSaveResponse rep=new OrderSaveResponse();
		UserContext u=new UserContext();

		OrderVo vo=new OrderVo();
		if(!StringUtil.isEmpty(action.getId()))
		{
			vo.setId(action.getId());
		}
		
		//点菜or订房
		if("DISHES".equals(action.getDishesOrRoomFal()))
		{
			//订房电话
			vo.setOrderPersonPhone(action.getOrderPersonPhone());
			vo.setPlaceOrderTime(new Date());
			vo.setRestaurantId(action.getRestaurantId());
			vo.setCity(action.getCity());
			if(action.getBookingDishesList()!=null && action.getBookingDishesList().size()>0)
			{
				List<OrderDishesVo> ordersDishesList=new ArrayList<OrderDishesVo>();
				for (BookingDishesList client:action.getBookingDishesList()) {
					OrderDishesVo dishes=new OrderDishesVo();
					dishes.setDishesId(client.getId());
					dishes.setNumber(client.getNumber());
					dishes.setPrice(client.getPrice());
					dishes.setTaste(client.getTaste());
					dishes.setUnit(client.getUnit());
					ordersDishesList.add(dishes);
				}
				vo.setOrdersDishesList(ordersDishesList);
			}
			Orders order=orderService.saveOrdersByPhoneDishes(u, vo);
			rep.setOrderId(order.getId());
			if(order.getRoomState()!=null)
				rep.setRoomState(order.getRoomState().toString());
			else
				rep.setRoomState(RoomState.NOTRESERVATION.toString());
		}
		else if("ROOM".equals(action.getDishesOrRoomFal()))
		{
			vo.setAmountOfClient(action.getAmountOfClient());
			vo.setCity(action.getCity());
			vo.setContactPersonName(action.getContactPersonName());
			vo.setContactPersonPhone(action.getContactPersonPhone());
			vo.setCorporationId(action.getCorporationId());
			vo.setFavoriteRoom(action.getFavoriteRoom());
			vo.setMemo(action.getMemo());
			vo.setOrderPersonName(action.getOrderPersonName());
			vo.setOrderPersonPhone(action.getOrderPersonPhone());
			vo.setPlaceOrderTime(new Date());
			vo.setReserveTimeDate(action.getReserveTimeDate());
			vo.setReserveTimeDateH(action.getReserveTimeDateH());
			vo.setReserveTimeDateS(action.getReserveTimeDateS());
			vo.setContactPersonSex(action.getContactPersonSex());
			vo.setOrderPersonSex(action.getOrderPersonSex());
			vo.setRestaurantId(action.getRestaurantId());
			if(action.getOrderStatus()!=null)
				vo.setOrderStatus(OrderStatus.valueOf(action.getOrderStatus().toString()));
			Orders order=orderService.saveOrdersByRoom(u, vo);
			rep.setOrderId(order.getId());
		}
		
		return rep;
	}

	@Override
	public void rollback(OrderSaveRequest action, OrderSaveResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
