package com.omdasoft.orderonline.gwt.order.server;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.dishes.DishesType;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.DishesTypeSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.DishesTypeSaveResponse;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.dishes.DishesService;
import com.omdasoft.orderonline.util.StringUtil;

public class DishesTypeSaveActionHandler extends
		BaseActionHandler<DishesTypeSaveRequest, DishesTypeSaveResponse> {

	DishesService dishesService;
	
	@Inject
	public DishesTypeSaveActionHandler(DishesService dishesService)
	{
		this.dishesService=dishesService;
	}
	public DishesTypeSaveActionHandler() {

	}
	@Override
	public Class<DishesTypeSaveRequest> getActionType() {
		return DishesTypeSaveRequest.class;
	}

	@Override
	public DishesTypeSaveResponse execute(DishesTypeSaveRequest action, ExecutionContext context)
			throws DispatchException {
		DishesTypeSaveResponse rep=new DishesTypeSaveResponse();
		UserContext u=new UserContext();
		u.setCorporationId(action.getSession().getCorporationId());
		u.setUserId(action.getSession().getToken());
		u.setDeptId(action.getSession().getDepartmentId());
		DishesType vo=new DishesType();
		if(!StringUtil.isEmptyString(action.getId()))
			vo.setId(action.getId());
		vo.setName(action.getName());
		vo.setDishesType(action.getDishesType());
		dishesService.saveDishesType(u, vo);

		return rep;
	}

	@Override
	public void rollback(DishesTypeSaveRequest action, DishesTypeSaveResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
