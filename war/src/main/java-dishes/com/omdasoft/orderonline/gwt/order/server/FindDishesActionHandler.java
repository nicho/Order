package com.omdasoft.orderonline.gwt.order.server;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.dishes.Dishes;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.request.FindDishesRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.request.FindDishesResponse;
import com.omdasoft.orderonline.service.dishes.DishesService;

public class FindDishesActionHandler extends
		BaseActionHandler<FindDishesRequest, FindDishesResponse> {

	DishesService dishesService;
	
	@Inject
	public FindDishesActionHandler(DishesService dishesService)
	{
		this.dishesService=dishesService;
	}
	public FindDishesActionHandler() {

	}
	@Override
	public Class<FindDishesRequest> getActionType() {
		return FindDishesRequest.class;
	}

	@Override
	public FindDishesResponse execute(FindDishesRequest action, ExecutionContext context)
			throws DispatchException {
		FindDishesResponse rep=new FindDishesResponse();

		Dishes dictionary= dishesService.findDishesById(action.getDishesId());
		rep.setName(dictionary.getName());
		rep.setDescription(dictionary.getDescription());
		if(dictionary.getDishesType()!=null)
		rep.setDishesType(dictionary.getDishesType().getId());
		rep.setPhoto(dictionary.getPhoto());
		rep.setPrice(dictionary.getPrice()+"");
		rep.setStatus(dictionary.getStatus());
		rep.setRid(dictionary.getRid());
		rep.setTaste(dictionary.getTaste());
		return rep;
	}

	@Override
	public void rollback(FindDishesRequest action, FindDishesResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
