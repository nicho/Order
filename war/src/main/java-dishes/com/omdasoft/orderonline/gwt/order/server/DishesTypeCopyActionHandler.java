package com.omdasoft.orderonline.gwt.order.server;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.DishesTypeCopyRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.DishesTypeCopyResponse;
import com.omdasoft.orderonline.service.dishes.DishesService;

public class DishesTypeCopyActionHandler extends
		BaseActionHandler<DishesTypeCopyRequest, DishesTypeCopyResponse> {

	DishesService dishesService;
	
	@Inject
	public DishesTypeCopyActionHandler(DishesService dishesService)
	{
		this.dishesService=dishesService;
	}
	public DishesTypeCopyActionHandler() {

	}
	@Override
	public Class<DishesTypeCopyRequest> getActionType() {
		return DishesTypeCopyRequest.class;
	}

	@Override
	public DishesTypeCopyResponse execute(DishesTypeCopyRequest action, ExecutionContext context)
			throws DispatchException {
		DishesTypeCopyResponse rep=new DishesTypeCopyResponse();

		String fal=dishesService.copyDishesType(action.getDeptId());
		rep.setFal(fal);
		return rep;
	}

	@Override
	public void rollback(DishesTypeCopyRequest action, DishesTypeCopyResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
