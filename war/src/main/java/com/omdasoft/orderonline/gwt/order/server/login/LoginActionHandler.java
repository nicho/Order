package com.omdasoft.orderonline.gwt.order.server.login;

import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.login.LoginRequest;
import com.omdasoft.orderonline.gwt.order.client.login.LoginResponse;
import com.omdasoft.orderonline.gwt.order.model.ClientException;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.util.UserRoleTool;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.model.user.UserSessionVo;
import com.omdasoft.orderonline.service.user.UserService;

public class LoginActionHandler extends
		BaseActionHandler<LoginRequest, LoginResponse> {
	UserService userService;

	@Inject
	public LoginActionHandler(UserService userService) {
		this.userService = userService;
	}

	public LoginActionHandler() {

	}

	@Override
	public Class<LoginRequest> getActionType() {
		return LoginRequest.class;
	}

	@Override
	public LoginResponse execute(LoginRequest action, ExecutionContext context)
			throws DispatchException {

		LoginResponse resp = new LoginResponse();
		UserSessionVo u = userService.authenticate(action.getUserName(),
				action.getPwd());
		if (u != null) {
			resp.setCorporationId(u.getCorporationId());
			resp.setCorporationName(u.getCorporationName());
			resp.setPhoto(u.getPhoto());
			resp.setLoginName(u.getUsername());
			resp.setToken(u.getId());
			resp.setDepartmentId(u.getDepartmentId());
			resp.setUserRoles(UserRoleTool.adaptToRoleVo(u.getUserRoles()));
			resp.setStaffId(u.getStaffId());
			resp.setCid(u.getCid());
			if(u.getLastLoginRole()!=null)
			{
				resp.setLastLoginRole(UserRoleVo.valueOf(u.getLastLoginRole().toString()));
			}
			else
			{
				List<UserRole> roles=u.getUserRoles();
				if(roles.size()>0)
				{
					if(roles.contains(UserRole.CORP_ADMIN))
						resp.setLastLoginRole(UserRoleVo.valueOf(UserRole.CORP_ADMIN.toString()));
					else if(roles.contains(UserRole.DEPT_MGR))
						resp.setLastLoginRole(UserRoleVo.valueOf(UserRole.DEPT_MGR.toString()));

				}
			}
		} else {
			throw new ClientException("用户名或密码错误!");
		}

		return resp;

	}

	@Override
	public void rollback(LoginRequest action, LoginResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
