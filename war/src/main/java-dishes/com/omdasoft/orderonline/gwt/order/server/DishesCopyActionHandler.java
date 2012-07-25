package com.omdasoft.orderonline.gwt.order.server;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.gwt.order.client.dishesList.request.DishesCopyRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.DishesCopyResponse;
import com.omdasoft.orderonline.service.dishes.DishesService;

public class DishesCopyActionHandler extends
		BaseActionHandler<DishesCopyRequest, DishesCopyResponse> {

	DishesService dishesService;
	
	@Inject
	public DishesCopyActionHandler(DishesService dishesService)
	{
		this.dishesService=dishesService;
	}
	public DishesCopyActionHandler() {

	}
	@Override
	public Class<DishesCopyRequest> getActionType() {
		return DishesCopyRequest.class;
	}

	@Override
	public DishesCopyResponse execute(DishesCopyRequest action, ExecutionContext context)
			throws DispatchException {
		DishesCopyResponse rep=new DishesCopyResponse();
		String fal=dishesService.copyDishes(action.getUserId());
		rep.setFal(fal);
		return rep;
	}

	@Override
	public void rollback(DishesCopyRequest action, DishesCopyResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
