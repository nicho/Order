package com.omdasoft.orderonline.service.staff.impl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.omdasoft.orderonline.dao.org.DepartmentManagerDao;
import com.omdasoft.orderonline.dao.org.StaffDao;
import com.omdasoft.orderonline.dao.org.StaffDao.QueryStaffPageActionResult;
import com.omdasoft.orderonline.dao.user.RoleDao;
import com.omdasoft.orderonline.dao.user.UserRoleDao;
import com.omdasoft.orderonline.domain.org.Staff;
import com.omdasoft.orderonline.model.staff.StaffProcess;
import com.omdasoft.orderonline.model.staff.StaffSearchCriteria;
import com.omdasoft.orderonline.model.staff.StaffUserProcess;
import com.omdasoft.orderonline.model.user.GeneratedUserConstants;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.service.staff.IStaffService;
import com.omdasoft.orderonline.service.staff.StaffLogic;
import com.omdasoft.orderonline.service.user.UserLogic;

@Transactional
public class StaffServiceImpl implements IStaffService {

	StaffDao staffDao;
	StaffLogic staffLogic;
	UserLogic userLogic;
	UserRoleDao userRoleDao;
	RoleDao roleDao;
	DepartmentManagerDao departmentManagerDao;

	@Inject
	public StaffServiceImpl(StaffDao staffDao, StaffLogic staffLogic,
			UserLogic userLogic, 
			UserRoleDao userRoleDao, RoleDao roleDao,DepartmentManagerDao departmentManagerDao) {
		this.staffDao = staffDao;
		this.staffLogic = staffLogic;
		this.userLogic = userLogic;
		this.userRoleDao = userRoleDao;
		this.roleDao = roleDao;
		this.departmentManagerDao=departmentManagerDao;
	}


	@Override
	public QueryStaffPageActionResult queryStaffList(StaffSearchCriteria criteria,
			UserContext context) {
		return staffLogic.queryStaffList(criteria, context);
	}

	@Override
	public String createOrUpdateStaff(StaffProcess staff, UserContext context) {
		String staffId=staffLogic.createOrUpdateStaff(staff, context);
		if(!"用户名已经存在".equals(staffId))
		staffLogic.generatedUserbyStaff(staffId, context,staff.getPassword());
		return "succsee";
	}

	@Override
	public Staff findStaffById(String staffId) {
		return staffLogic.findStaffById(staffId);
	}

	

	@Override
	public GeneratedUserConstants generatedUserbyStaff(String staffId, UserContext context) {
		return staffLogic.generatedUserbyStaff(staffId, context,"123");
	}
	@Override
	public String createHrUser(StaffUserProcess staff){
		return staffLogic.createHrUser( staff);
	}
	@Override
	public Staff updateLeadTime(String staffId,int leadTime){
		return staffLogic.updateLeadTime(staffId, leadTime);
	}

	@Override
	public List<UserRole> findUserRoles(String staffId) {
		return staffLogic.findUserRoles(staffId);
	}


	@Override
	public int deleteStaffById(UserContext context, String staffId) {
		return staffLogic.deleteStaffById(context, staffId);
	}


	@Override
	public int enabledStaffById(UserContext context, String staffId) {
		return staffLogic.enabledStaffById(context, staffId);
	}
}
