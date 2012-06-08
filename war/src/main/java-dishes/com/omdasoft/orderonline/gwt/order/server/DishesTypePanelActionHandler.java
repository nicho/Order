package com.omdasoft.orderonline.gwt.order.server;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.DishesTypePanelRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.DishesTypePanelResponse;
import com.omdasoft.orderonline.service.dishes.DishesService;

public class DishesTypePanelActionHandler extends
		BaseActionHandler<DishesTypePanelRequest, DishesTypePanelResponse> {

	DishesService dishesService;
	
	@Inject
	public DishesTypePanelActionHandler(DishesService dishesService)
	{
		this.dishesService=dishesService;
	}
	public DishesTypePanelActionHandler() {

	}
	@Override
	public Class<DishesTypePanelRequest> getActionType() {
		return DishesTypePanelRequest.class;
	}

	@Override
	public DishesTypePanelResponse execute(DishesTypePanelRequest action, ExecutionContext context)
			throws DispatchException {
		return new DishesTypePanelResponse(dishesService.findDishesTypePanel());
	}

	@Override
	public void rollback(DishesTypePanelRequest action, DishesTypePanelResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
