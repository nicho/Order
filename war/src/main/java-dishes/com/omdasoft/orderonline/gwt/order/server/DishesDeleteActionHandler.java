package com.omdasoft.orderonline.gwt.order.server;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.gwt.order.client.dishesList.request.DishesDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.DishesDeleteResponse;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.dishes.DishesService;

public class DishesDeleteActionHandler extends
		BaseActionHandler<DishesDeleteRequest, DishesDeleteResponse> {

	DishesService dishesService;
	
	@Inject
	public DishesDeleteActionHandler(DishesService dishesService)
	{
		this.dishesService=dishesService;
	}
	public DishesDeleteActionHandler() {

	}
	@Override
	public Class<DishesDeleteRequest> getActionType() {
		return DishesDeleteRequest.class;
	}

	@Override
	public DishesDeleteResponse execute(DishesDeleteRequest action, ExecutionContext context)
			throws DispatchException {
		DishesDeleteResponse rep=new DishesDeleteResponse();
		UserContext u=new UserContext();
		u.setUserId(action.getSession().getToken());
		
		rep.setTotal(dishesService.deleteDishes(u, action.getId()));
		
		return rep;
	}

	@Override
	public void rollback(DishesDeleteRequest action, DishesDeleteResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
