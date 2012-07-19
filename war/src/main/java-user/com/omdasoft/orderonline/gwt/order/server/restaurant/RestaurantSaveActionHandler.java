package com.omdasoft.orderonline.gwt.order.server.restaurant;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.RestaurantSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.RestaurantSaveResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.org.DepartmentService;
import com.omdasoft.orderonline.service.user.UserService;
import com.omdasoft.orderonline.util.StringUtil;

public class RestaurantSaveActionHandler extends
		BaseActionHandler<RestaurantSaveRequest, RestaurantSaveResponse> {

	DepartmentService departmentService;
	UserService userService;
	@Inject
	public RestaurantSaveActionHandler(DepartmentService departmentService,UserService userService)
	{
		this.departmentService=departmentService;
		this.userService=userService;
	}
	public RestaurantSaveActionHandler() {

	}
	@Override
	public Class<RestaurantSaveRequest> getActionType() {
		return RestaurantSaveRequest.class;
	}

	@Override
	public RestaurantSaveResponse execute(RestaurantSaveRequest action, ExecutionContext context)
			throws DispatchException {
		RestaurantSaveResponse rep=new RestaurantSaveResponse();
		UserContext u=new UserContext();
		u.setCorporationId(action.getSession().getCorporationId());
		u.setUserId(action.getSession().getToken());
		Department vo=new Department();
		if(!StringUtil.isEmptyString(action.getId()))
			vo.setId(action.getId());
		vo.setName(action.getName());
		vo.setCity(action.getCity());
		vo.setAddress(action.getAddress());
		vo.setPhone(action.getPhone());
		vo.setDid(action.getDid());
		vo.setParent(departmentService.findDepartmentById(action.getSession().getDepartmentId()));
		if(!StringUtil.isEmptyString(action.getDeptAdminStaffId()))
		{
			vo.setDeptAdmin(userService.findUserByStaffId(action.getDeptAdminStaffId()));
		}
	
		departmentService.saveDepartment(u, vo, null, null);
		return rep;
	}

	@Override
	public void rollback(RestaurantSaveRequest action, RestaurantSaveResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
