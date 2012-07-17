package com.omdasoft.orderonline.gwt.order.server;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.dishes.Dishes;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.request.DishesSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.request.DishesSaveResponse;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.dishes.DishesService;
import com.omdasoft.orderonline.util.StringUtil;

public class DishesSaveActionHandler extends
		BaseActionHandler<DishesSaveRequest, DishesSaveResponse> {

	DishesService dishesService;
	
	@Inject
	public DishesSaveActionHandler(DishesService dishesService)
	{
		this.dishesService=dishesService;
	}
	public DishesSaveActionHandler() {

	}
	@Override
	public Class<DishesSaveRequest> getActionType() {
		return DishesSaveRequest.class;
	}

	@Override
	public DishesSaveResponse execute(DishesSaveRequest action, ExecutionContext context)
			throws DispatchException {
		DishesSaveResponse rep=new DishesSaveResponse();
		UserContext u=new UserContext();
		u.setCorporationId(action.getSession().getCorporationId());
		u.setUserId(action.getSession().getToken());
		u.setDeptId(action.getSession().getDepartmentId());
		Dishes vo=new Dishes();
		if(!StringUtil.isEmptyString(action.getId()))
			vo.setId(action.getId());
		vo.setName(action.getName());
		vo.setDescription(action.getDescription());
		vo.setPhoto(action.getPhoto());
		vo.setPrice(Double.parseDouble(action.getPrice()));
		vo.setStatus(action.getStatus());
		vo.setTaste(action.getTaste());
		if(!StringUtil.isEmptyString(action.getDishesType()))
			vo.setDishesType(dishesService.findDishesTypeById(action.getDishesType()));
		dishesService.saveDishes(u, vo);

		return rep;
	}

	@Override
	public void rollback(DishesSaveRequest action, DishesSaveResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
