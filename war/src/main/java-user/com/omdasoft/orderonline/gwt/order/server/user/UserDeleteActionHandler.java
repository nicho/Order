package com.omdasoft.orderonline.gwt.order.server.user;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.gwt.order.client.userList.request.UserDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.userList.request.UserDeleteResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.staff.IStaffService;

public class UserDeleteActionHandler extends
		BaseActionHandler<UserDeleteRequest, UserDeleteResponse> {

	IStaffService staffService;
	
	@Inject
	public UserDeleteActionHandler(IStaffService staffService)
	{
		this.staffService=staffService;
	}
	public UserDeleteActionHandler() {

	}
	@Override
	public Class<UserDeleteRequest> getActionType() {
		return UserDeleteRequest.class;
	}

	@Override
	public UserDeleteResponse execute(UserDeleteRequest action, ExecutionContext context)
			throws DispatchException {
		UserDeleteResponse rep=new UserDeleteResponse();
		UserContext u=new UserContext();
		u.setUserId(action.getSession().getToken());
		
		if(action.getStatusfal()==1)
			rep.setTotal(staffService.deleteStaffById(u, action.getId()));
		else if(action.getStatusfal()==0)
			rep.setTotal(staffService.enabledStaffById(u, action.getId()));
		return rep;
	}

	@Override
	public void rollback(UserDeleteRequest action, UserDeleteResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
