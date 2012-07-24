package com.omdasoft.orderonline.gwt.order.server.login;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.login.LastLoginRoleRequest;
import com.omdasoft.orderonline.gwt.order.client.login.LastLoginRoleResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.service.user.UserService;

public class UpdatelastLoginRoleActionHandler extends
		BaseActionHandler<LastLoginRoleRequest, LastLoginRoleResponse> {

	UserService userService;

	@Inject
	public UpdatelastLoginRoleActionHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Class<LastLoginRoleRequest> getActionType() {
		return LastLoginRoleRequest.class;
	}

	@Override
	public LastLoginRoleResponse execute(LastLoginRoleRequest action,
			ExecutionContext context) throws DispatchException {
		String fal ="";
		if(action.getRole()!=null)	
			fal = userService.updateLastLoginRole(action.getUserId(),UserRole.valueOf(action.getRole().toString()));
		else
			fal = userService.updateLastLoginRole(action.getUserId(),null);
		return new LastLoginRoleResponse(fal);
	}

	@Override
	public void rollback(LastLoginRoleRequest action,
			LastLoginRoleResponse result, ExecutionContext context)
			throws DispatchException {

	}

}
