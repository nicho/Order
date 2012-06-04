package com.omdasoft.orderonline.gwt.order.server;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.dishes.DishesType;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.FindDishesTypeRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.FindDishesTypeResponse;
import com.omdasoft.orderonline.service.dishes.DishesService;

public class FindDishesTypeActionHandler extends
		BaseActionHandler<FindDishesTypeRequest, FindDishesTypeResponse> {

	DishesService dishesService;
	
	@Inject
	public FindDishesTypeActionHandler(DishesService dishesService)
	{
		this.dishesService=dishesService;
	}
	public FindDishesTypeActionHandler() {

	}
	@Override
	public Class<FindDishesTypeRequest> getActionType() {
		return FindDishesTypeRequest.class;
	}

	@Override
	public FindDishesTypeResponse execute(FindDishesTypeRequest action, ExecutionContext context)
			throws DispatchException {
		FindDishesTypeResponse rep=new FindDishesTypeResponse();

		DishesType dictionary= dishesService.findDishesTypeById(action.getDishesTypeId());
		rep.setName(dictionary.getName());
		rep.setDishesType(dictionary.getDishesType());
		rep.setRid(dictionary.getRid());

		return rep;
	}

	@Override
	public void rollback(FindDishesTypeRequest action, FindDishesTypeResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
