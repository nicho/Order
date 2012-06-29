package com.omdasoft.orderonline.gwt.order.server.ordersLogin;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.order.Orders;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria.OrderStatus;
import com.omdasoft.orderonline.gwt.order.client.ordersWait.request.OrderWaitRequest;
import com.omdasoft.orderonline.gwt.order.client.ordersWait.request.OrderWaitResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.service.order.OrderService;

public class OrderWaitActionHandler extends
		BaseActionHandler<OrderWaitRequest, OrderWaitResponse> {

	OrderService orderService;
	
	@Inject
	public OrderWaitActionHandler(OrderService orderService)
	{
		this.orderService=orderService;
	}
	public OrderWaitActionHandler() {

	}
	@Override
	public Class<OrderWaitRequest> getActionType() {
		return OrderWaitRequest.class;
	}

	@Override
	public OrderWaitResponse execute(OrderWaitRequest action, ExecutionContext context)
			throws DispatchException {
		OrderWaitResponse rep=new OrderWaitResponse();
		Orders order=orderService.findOrderById(action.getOrderId());
		if(order!=null && order.getOrderStatus()!=null)
		rep.setStatus(OrderStatus.valueOf(order.getOrderStatus().toString()));
		return rep;
	}

	@Override
	public void rollback(OrderWaitRequest action, OrderWaitResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
