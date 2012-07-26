package com.omdasoft.orderonline.gwt.order.server.login;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.login.TokenValidRequest;
import com.omdasoft.orderonline.gwt.order.client.login.TokenValidResponse;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.util.UserRoleTool;
import com.omdasoft.orderonline.model.user.UserSessionVo;
import com.omdasoft.orderonline.service.user.UserService;

public class TokenValidActionHandler extends
		BaseActionHandler<TokenValidRequest, TokenValidResponse> {

	UserService userService;
	
	@Inject
	public TokenValidActionHandler(UserService userService)
	{
		this.userService=userService;
	}
	public TokenValidActionHandler() {

	}
	@Override
	public Class<TokenValidRequest> getActionType() {
		return TokenValidRequest.class;
	}

	@Override
	public TokenValidResponse execute(TokenValidRequest action, ExecutionContext context)
			throws DispatchException {
		TokenValidResponse tokenRep=new TokenValidResponse();
		UserSessionVo userSessionVo=userService.tokenVaild(action.getToken());
		if(userSessionVo !=null){
		tokenRep.setCorporationId(userSessionVo.getCorporationId());
		tokenRep.setLoginName(userSessionVo.getUsername());
		tokenRep.setToken(userSessionVo.getId());
		tokenRep.setUserRoles(UserRoleTool.adaptToRoleVo(userSessionVo.getUserRoles()));
		tokenRep.setDepartmentId(userSessionVo.getDepartmentId());
		tokenRep.setStaffId(userSessionVo.getStaffId());
		tokenRep.setCorporationName(userSessionVo.getCorporationName());
		tokenRep.setPhoto(userSessionVo.getPhoto());
		tokenRep.setCid(userSessionVo.getCid());
		tokenRep.setDepartmentName(userSessionVo.getDepartmentName());
		if(userSessionVo.getLastLoginRole()!=null)
		{
			tokenRep.setLastLoginRole(UserRoleVo.valueOf(userSessionVo.getLastLoginRole().toString()));
		}
		}
		return tokenRep;
	}

	@Override
	public void rollback(TokenValidRequest action, TokenValidResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
