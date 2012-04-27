package com.omdasoft.orderonline.gwt.order.server.user;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.userAdd.request.UserAddRequest;
import com.omdasoft.orderonline.gwt.order.client.userAdd.request.UserAddResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.server.logger.InjectLogger;
import com.omdasoft.orderonline.gwt.order.util.UserRoleTool;
import com.omdasoft.orderonline.model.staff.StaffProcess;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.service.staff.IStaffService;

/**
 * The handler to correspond the UserAddRequest.
 * 
 * @author nicho
 * @since 2011年12月7日 09:41:42
 */
public class UserAddActionHandler extends
		BaseActionHandler<UserAddRequest, UserAddResponse> {

	@InjectLogger
	Logger logger;

	IStaffService staffService;


	@Inject
	public UserAddActionHandler(IStaffService staffService) {
		this.staffService = staffService;
	}

	@Override
	public UserAddResponse execute(UserAddRequest request,
			ExecutionContext response) throws DispatchException {

		UserContext context=new UserContext();
		context.setCorporationId(request.getSession().getCorporationId());
		context.setUserId(request.getSession().getToken());
		context.setLoginName(request.getSession().getLoginName());
		context.setUserRoles(UserRoleTool.adaptToRole(request.getSession().getUserRoles()));
	
		StaffProcess sp=new StaffProcess();
		sp.setStaffId(request.getStaffId());
		sp.setStaffNo(request.getStaffNo());
		sp.setStaffName(request.getStaffName());
		sp.setDepartmentId(request.getDepartmentId());
		sp.setPhone(request.getPhone());
		sp.setEmail(request.getEmail());
		sp.setPassword(request.getPwd());
		if(request.getUserRoleVos()!=null && request.getUserRoleVos().size()>0)
		{
			List<UserRole> roles=new ArrayList<UserRole>();
			for (int i = 0; i < request.getUserRoleVos().size(); i++) {
				roles.add(UserRole.valueOf(request.getUserRoleVos().get(i).toString()));
			}
			sp.setUserRoleVos(roles);
		}
		else if(request.getUserRoleVos()!=null)
		{
			List<UserRole> roles=new ArrayList<UserRole>();
			sp.setUserRoleVos(roles);
		}
		String staffId=staffService.createOrUpdateStaff(sp, context);
		return  new UserAddResponse(staffId);
	}

	@Override
	public Class<UserAddRequest> getActionType() {
		return UserAddRequest.class;
	}

	@Override
	public void rollback(UserAddRequest request,
			UserAddResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
