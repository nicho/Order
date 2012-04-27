package com.omdasoft.orderonline.gwt.order.server;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.DishesTypeDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.DishesTypeDeleteResponse;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.dishes.DishesService;

public class DishesTypeDeleteActionHandler extends
		BaseActionHandler<DishesTypeDeleteRequest, DishesTypeDeleteResponse> {

	DishesService dishesService;
	
	@Inject
	public DishesTypeDeleteActionHandler(DishesService dishesService)
	{
		this.dishesService=dishesService;
	}
	public DishesTypeDeleteActionHandler() {

	}
	@Override
	public Class<DishesTypeDeleteRequest> getActionType() {
		return DishesTypeDeleteRequest.class;
	}

	@Override
	public DishesTypeDeleteResponse execute(DishesTypeDeleteRequest action, ExecutionContext context)
			throws DispatchException {
		DishesTypeDeleteResponse rep=new DishesTypeDeleteResponse();
		UserContext u=new UserContext();
		u.setUserId(action.getSession().getToken());
		rep.setTotal(dishesService.deleteDishesType(u, action.getId()));
		return rep;
	}

	@Override
	public void rollback(DishesTypeDeleteRequest action, DishesTypeDeleteResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
