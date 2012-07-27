package com.omdasoft.orderonline.gwt.order.client.server.order;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.order.Orders;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria.OrderStatus;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.FindOrderRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.FindOrderResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.service.order.OrderService;

public class FindOrderActionHandler extends
		BaseActionHandler<FindOrderRequest, FindOrderResponse> {

	OrderService OrderService;
	
	@Inject
	public FindOrderActionHandler(OrderService OrderService)
	{
		this.OrderService=OrderService;
	}
	public FindOrderActionHandler() {

	}
	@Override
	public Class<FindOrderRequest> getActionType() {
		return FindOrderRequest.class;
	}

	@Override
	public FindOrderResponse execute(FindOrderRequest action, ExecutionContext context)
			throws DispatchException {
		FindOrderResponse rep=new FindOrderResponse();

		Orders order= OrderService.findOrderById(action.getOrderId());
		rep.setAmountOfClient(order.getAmountOfClient());
		rep.setCity(order.getCity());
		rep.setFavoriteRoom(order.getFavoriteRoom());
		rep.setMemo(order.getMemo());
		rep.setPlaceOrderTime(order.getPlaceOrderTime());
		rep.setReserveTimeDate(order.getReserveTimeDate());
		rep.setReserveTimeDateH(order.getReserveTimeDateH());
		rep.setReserveTimeDateS(order.getReserveTimeDateS());
		if(order.getOrderPerson()!=null)
		{
		rep.setOrderPersonName(order.getOrderPerson().getName());
		rep.setOrderPersonPhone(order.getOrderPerson().getPhone());
		rep.setOrderPersonSex(order.getOrderPerson().getSex());
		}
		if(order.getContactPerson()!=null)
		{
		rep.setContactPersonName(order.getContactPerson().getName());
		rep.setContactPersonPhone(order.getContactPerson().getPhone());
		rep.setContactPersonSex(order.getContactPerson().getSex());
		}
		if(order.getDepartment()!=null)
		{
		rep.setRestaurantId(order.getDepartment().getId());
		rep.setRestaurantName(order.getDepartment().getName());
		}
		rep.setResult(order.getResult());
		rep.setStatus(OrderStatus.valueOf(order.getOrderStatus().toString()));
		return rep;
	}

	@Override
	public void rollback(FindOrderRequest action, FindOrderResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
