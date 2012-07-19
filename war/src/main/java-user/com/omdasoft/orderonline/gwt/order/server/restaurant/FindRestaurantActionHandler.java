package com.omdasoft.orderonline.gwt.order.server.restaurant;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.FindRestaurantRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.FindRestaurantResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.service.org.DepartmentService;

public class FindRestaurantActionHandler extends
		BaseActionHandler<FindRestaurantRequest, FindRestaurantResponse> {

	DepartmentService departmentService;
	
	@Inject
	public FindRestaurantActionHandler(DepartmentService departmentService)
	{
		this.departmentService=departmentService;
	}
	public FindRestaurantActionHandler() {

	}
	@Override
	public Class<FindRestaurantRequest> getActionType() {
		return FindRestaurantRequest.class;
	}

	@Override
	public FindRestaurantResponse execute(FindRestaurantRequest action, ExecutionContext context)
			throws DispatchException {
		FindRestaurantResponse rep=new FindRestaurantResponse();
		Department dictionary =departmentService.findDepartmentById(action.getRestaurantId());
		rep.setName(dictionary.getName());
		rep.setCity(dictionary.getCity());
		rep.setAddress(dictionary.getAddress());
		rep.setPhone(dictionary.getPhone());
		rep.setDid(dictionary.getDid());
		if(dictionary.getDeptAdmin()!=null && dictionary.getDeptAdmin().getStaff()!=null)
		{
		rep.setDeptAdminStaffId(dictionary.getDeptAdmin().getStaff().getId());
		rep.setDeptAdminStaffName(dictionary.getDeptAdmin().getStaff().getName());
		}
		return rep;
	}

	@Override
	public void rollback(FindRestaurantRequest action, FindRestaurantResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
