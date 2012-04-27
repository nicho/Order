package com.omdasoft.orderonline.gwt.order.server.restaurant;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.gwt.order.client.restaurantList.request.RestaurantDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.request.RestaurantDeleteResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.org.exception.DepartmentDeleteException;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.org.DepartmentService;

public class RestaurantDeleteActionHandler extends
		BaseActionHandler<RestaurantDeleteRequest, RestaurantDeleteResponse> {

	DepartmentService departmentService;
	
	@Inject
	public RestaurantDeleteActionHandler(DepartmentService departmentService)
	{
		this.departmentService=departmentService;
	}
	public RestaurantDeleteActionHandler() {

	}
	@Override
	public Class<RestaurantDeleteRequest> getActionType() {
		return RestaurantDeleteRequest.class;
	}

	@Override
	public RestaurantDeleteResponse execute(RestaurantDeleteRequest action, ExecutionContext context)
			throws DispatchException {
		RestaurantDeleteResponse rep=new RestaurantDeleteResponse();
		UserContext u=new UserContext();
		u.setUserId(action.getSession().getToken());
		
		try {
			departmentService.deleteDepartment(action.getId());
			rep.setTotal(1);
		} catch (DepartmentDeleteException e) {
			rep.setTotal(0);
			e.printStackTrace();
		}
		
		return rep;
	}

	@Override
	public void rollback(RestaurantDeleteRequest action, RestaurantDeleteResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
