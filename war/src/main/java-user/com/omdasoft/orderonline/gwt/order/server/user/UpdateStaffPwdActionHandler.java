package com.omdasoft.orderonline.gwt.order.server.user;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.password.request.PasswordRequest;
import com.omdasoft.orderonline.gwt.order.client.password.request.PasswordResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.server.logger.InjectLogger;
import com.omdasoft.orderonline.service.user.UserService;

/**
 * The handler to correspond the PasswordRequest.
 * 
 * @author nicho
 * @since 2012年2月16日 14:57:56
 */
public class UpdateStaffPwdActionHandler extends
		BaseActionHandler<PasswordRequest, PasswordResponse> {

	@InjectLogger
	Logger logger;
	UserService userService;

	@Inject
	public UpdateStaffPwdActionHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public PasswordResponse execute(PasswordRequest request,
			ExecutionContext response) throws DispatchException {

		String message = userService.updateStaffPwd(request.getUserId(),request.getOldpwd(),
				request.getPwd(), request.getSession().getToken());
		return new PasswordResponse(message);

	}

	@Override
	public Class<PasswordRequest> getActionType() {
		return PasswordRequest.class;
	}

	@Override
	public void rollback(PasswordRequest request,
			PasswordResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
