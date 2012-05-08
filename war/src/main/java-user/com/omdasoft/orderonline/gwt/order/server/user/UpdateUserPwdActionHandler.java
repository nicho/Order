package com.omdasoft.orderonline.gwt.order.server.user;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.userList.request.UpdateUserPwdRequest;
import com.omdasoft.orderonline.gwt.order.client.userList.request.UpdateUserPwdResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.server.logger.InjectLogger;
import com.omdasoft.orderonline.service.user.UserService;

/**
 * The handler to correspond the UpdateUserPwdRequest.
 * 
 * @author nicho
 * @since 2012年2月16日 14:57:56
 */
public class UpdateUserPwdActionHandler extends
		BaseActionHandler<UpdateUserPwdRequest, UpdateUserPwdResponse> {

	@InjectLogger
	Logger logger;
	UserService userService;

	@Inject
	public UpdateUserPwdActionHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UpdateUserPwdResponse execute(UpdateUserPwdRequest request,
			ExecutionContext response) throws DispatchException {

		String message = userService.updateUserPwd(request.getStaffId(),
				request.getPwd(), request.getSession().getToken());
		return new UpdateUserPwdResponse(message);

	}

	@Override
	public Class<UpdateUserPwdRequest> getActionType() {
		return UpdateUserPwdRequest.class;
	}

	@Override
	public void rollback(UpdateUserPwdRequest request,
			UpdateUserPwdResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
