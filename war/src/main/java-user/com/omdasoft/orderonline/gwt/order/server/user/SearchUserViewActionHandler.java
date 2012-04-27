package com.omdasoft.orderonline.gwt.order.server.user;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.google.inject.Inject;
import com.omdasoft.orderonline.domain.org.Staff;
import com.omdasoft.orderonline.gwt.order.client.userView.request.UserViewRequest;
import com.omdasoft.orderonline.gwt.order.client.userView.request.UserViewResponse;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.server.logger.InjectLogger;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.service.staff.IStaffService;

/**
 * The handler to correspond the UserViewRequest.
 * 
 * @author nicho
 * @since 2012年2月15日 10:04:40
 */
public class SearchUserViewActionHandler extends
		BaseActionHandler<UserViewRequest, UserViewResponse> {

	@InjectLogger
	Logger logger;

	IStaffService staffService;


	@Inject
	public SearchUserViewActionHandler(IStaffService staffService) {
		this.staffService = staffService;
	}

	@Override
	public UserViewResponse execute(UserViewRequest request,
			ExecutionContext response) throws DispatchException {
		UserViewResponse staffResponse=new UserViewResponse();
		Staff staff=staffService.findStaffById(request.getStaffId());
		staffResponse.setStaffId(staff.getId());
		staffResponse.setStaffNo(staff.getJobNo());
		staffResponse.setStaffName(staff.getName());
		if(staff.getLeadTime()!=null)
		   staffResponse.setLeadTime(staff.getLeadTime());//新加的返回的提前颁奖通知时间
		else
			staffResponse.setLeadTime(0);
		if(staff.getDepartment()!=null)
		{
		staffResponse.setDepartmentId(staff.getDepartment().getId());
		staffResponse.setDepartmentName(staff.getDepartment().getName());
		}
		staffResponse.setPhoto(staff.getPhoto());
		staffResponse.setJobPosition(staff.getJobPosition());
		staffResponse.setLeadership(staff.getLeadership());
		staffResponse.setPhone(staff.getPhone());
		staffResponse.setEmail(staff.getEmail());
		staffResponse.setDob(staff.getDob());

		List<UserRoleVo> userRoleVos=new ArrayList<UserRoleVo>();
		 
		List<UserRole> userRoles=staffService.findUserRoles(staff.getId());
		if(userRoles!=null && userRoles.size()>0)
		{
			for (UserRole r:userRoles) {
				userRoleVos.add(UserRoleVo.valueOf(r.toString()));
			}
		}
		staffResponse.setUserRoleVos(userRoleVos);
		
		return staffResponse;
	}

	@Override
	public Class<UserViewRequest> getActionType() {
		return UserViewRequest.class;
	}

	@Override
	public void rollback(UserViewRequest request,
			UserViewResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
