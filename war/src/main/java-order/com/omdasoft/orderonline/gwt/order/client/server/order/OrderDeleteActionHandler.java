package com.omdasoft.orderonline.gwt.order.client.server.order;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.gwt.order.client.orderList.request.OrderDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.orderList.request.OrderDeleteResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.order.OrderService;

public class OrderDeleteActionHandler extends
		BaseActionHandler<OrderDeleteRequest, OrderDeleteResponse> {

	OrderService orderService;
	
	@Inject
	public OrderDeleteActionHandler(OrderService orderService)
	{
		this.orderService=orderService;
	}
	public OrderDeleteActionHandler() {

	}
	@Override
	public Class<OrderDeleteRequest> getActionType() {
		return OrderDeleteRequest.class;
	}

	@Override
	public OrderDeleteResponse execute(OrderDeleteRequest action, ExecutionContext context)
			throws DispatchException {
		OrderDeleteResponse rep=new OrderDeleteResponse();
		UserContext u=new UserContext();
		u.setUserId(action.getSession().getToken());
		
		orderService.deleteOrder(u, action.getId());
		rep.setTotal(1);
		return rep;
	}

	@Override
	public void rollback(OrderDeleteRequest action, OrderDeleteResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
