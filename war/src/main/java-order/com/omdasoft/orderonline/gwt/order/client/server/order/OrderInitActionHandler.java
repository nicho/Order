package com.omdasoft.orderonline.gwt.order.client.server.order;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderInitRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderInitResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.service.org.DepartmentService;

public class OrderInitActionHandler extends
		BaseActionHandler<OrderInitRequest, OrderInitResponse> {

	DepartmentService departmentService;
	
	@Inject
	public OrderInitActionHandler(DepartmentService departmentService)
	{
		this.departmentService=departmentService;
	}
	public OrderInitActionHandler() {

	}
	@Override
	public Class<OrderInitRequest> getActionType() {
		return OrderInitRequest.class;
	}

	@Override
	public OrderInitResponse execute(OrderInitRequest action, ExecutionContext context)
			throws DispatchException {
		OrderInitResponse rep=new OrderInitResponse();

		rep.setCityName(departmentService.getDepartmentCityName(action.getCorpId()));
		
		return rep;
	}

	@Override
	public void rollback(OrderInitRequest action, OrderInitResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
