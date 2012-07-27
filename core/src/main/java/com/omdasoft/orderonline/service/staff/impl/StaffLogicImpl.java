package com.omdasoft.orderonline.service.staff.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.omdasoft.orderonline.dao.org.CorporationDao;
import com.omdasoft.orderonline.dao.org.StaffDao;
import com.omdasoft.orderonline.dao.org.StaffDao.QueryStaffPageActionResult;
import com.omdasoft.orderonline.dao.user.RoleDao;
import com.omdasoft.orderonline.dao.user.UserDao;
import com.omdasoft.orderonline.dao.user.UserRoleDao;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.domain.org.Staff;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.domain.user.SysUserRole;
import com.omdasoft.orderonline.model.staff.StaffProcess;
import com.omdasoft.orderonline.model.staff.StaffSearchCriteria;
import com.omdasoft.orderonline.model.staff.StaffSearchVo;
import com.omdasoft.orderonline.model.staff.StaffStatus;
import com.omdasoft.orderonline.model.staff.StaffUserProcess;
import com.omdasoft.orderonline.model.user.GeneratedUserConstants;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.model.user.UserStatus;
import com.omdasoft.orderonline.service.org.CorporationLogic;
import com.omdasoft.orderonline.service.org.DepartmentLogic;
import com.omdasoft.orderonline.service.staff.StaffLogic;
import com.omdasoft.orderonline.util.DateUtil;
import com.omdasoft.orderonline.util.MD5;
import com.omdasoft.orderonline.util.StringUtil;

public class StaffLogicImpl implements StaffLogic {

	Logger logger = LoggerFactory.getLogger(getClass());

	private final StaffDao staffDao;
	private final DepartmentLogic deptLogic;
	private final CorporationLogic corporationLogic;
	private final UserDao userDao;
	private final UserRoleDao userRoleDao;
	private final RoleDao roleDao;
	private final CorporationDao corporationDao;

	MD5 md5 = new MD5();

	@Inject
	public StaffLogicImpl(CorporationDao corporationDao,StaffDao staffDao, DepartmentLogic deptLogic,
			CorporationLogic corporationLogic,
			 UserDao userDao,
			 UserRoleDao userRoleDao, RoleDao roleDao) {
		this.staffDao = staffDao;
		this.deptLogic = deptLogic;
		this.corporationLogic = corporationLogic;
		this.userDao = userDao;
		this.corporationDao=corporationDao;
		this.userRoleDao = userRoleDao;
		this.roleDao = roleDao;

	}

	@Override
	public List<Staff> getStaffsFromDeptId(String deptId,
			boolean includeChildren) {
		logger.debug(
				"Invoking method getStaffsFromDeptId, param[deptId={}, includeChildren={}]",
				new Object[] { deptId, includeChildren });
		if (includeChildren) {
			Department dept = deptLogic.findDepartmentById(deptId);
			String corpId = dept.getCorporation().getId();
			List<Staff> result = staffDao.findStaffsByDepartmentLftRgt(corpId,
					dept.getLft(), dept.getRgt());
			logger.debug("The gotten staff size={}", result.size());
			return result;
		} else {
			return staffDao.findStaffsByDepartmentId(deptId);
		}
	}

	

	@Override
	public List<Staff> findStaffsByStaffIds(List<String> staffIds) {

		return staffDao.findStaffsByStaffIds(staffIds);

	}

	@Override
	public List<Staff> getStaffsFromCorporationId(String corporationId) {

		List<Staff> result = staffDao.findStaffsByCorporationId(corporationId);
		logger.debug("The gotten staff size={}", result.size());
		return result;

	}

	@Override
	public QueryStaffPageActionResult queryStaffList(
			StaffSearchCriteria criteria, UserContext context) {
		StaffSearchVo searchVo = new StaffSearchVo();
		// 待添加查询条件
		if (!StringUtil.isEmptyString(criteria.getStaffNameorNo()))
			searchVo.setKeywords(criteria.getStaffNameorNo());
		if (criteria.getStaffStatus() != null)
			searchVo.setStatus(criteria.getStaffStatus());
		if (context.getCorporationId() != null)
			searchVo.setEnterpriseId(context.getCorporationId());
		if (criteria.getStaffRole() != null) {
			List<String> qstaffIds = new ArrayList<String>();
			List<String> staffIds = userRoleDao.findStaffIdsByRole(criteria
					.getStaffRole());
			if (staffIds != null && staffIds.size() > 0) {
				qstaffIds = staffIds;
			} else {
				qstaffIds.add("notStaffId");
			}
			searchVo.setStaffids(qstaffIds);
		}

		searchVo.setPaginationDetail(criteria.getPaginationDetail());
		searchVo.setSortingDetail(criteria.getSortingDetail());

//		// 如果员工界面..不过滤
//		if (!criteria.isColleaguePage()) {
//			// 处理部门管理员..进入..只查询本部门数据
//			boolean fal = false;
//			for (UserRole role : context.getUserRoles()) {
//				if (role == UserRole.CORP_ADMIN) {
//					fal = true;
//				}
//			}
//			if (fal == false) {
//				SysUser user = userDao.findUserById(context.getUserId());
//				if (user != null && user.getStaff() != null) {
//					List<String> departmentIds = deptMgrDao
//							.findDepartmentIdsManagedByStaffId(user.getStaff()
//									.getId());
//					if (departmentIds.size() > 0) {
//						Set<String> allDepartmentIds = new HashSet<String>();
//						for (String id : departmentIds) {
//							allDepartmentIds.addAll(departmentLogic
//									.getWholeChildrenIds(id, true));
//						}
//						searchVo.setDeptParam(new MultipleIdParam(
//								allDepartmentIds));
//					}
//
//				}
//
//			}
//		}
		return staffDao.queryStaffPageAction(searchVo);
	}

	@Override
	public String createOrUpdateStaff(StaffProcess staff, UserContext context) {
		Staff ff = null;
		SysUser nowuser = userDao.findUserById(context.getUserId());
		if (StringUtil.isEmptyString(staff.getStaffId())) {
			ff = new Staff();
		} else {
			ff = staffDao.findById(Staff.class, staff.getStaffId());
		}

		if (!StringUtil.isEmptyString(staff.getDepartmentId())) {
			Department dept = deptLogic.findDepartmentById(staff
					.getDepartmentId());
			ff.setDepartment(dept);
		} else {
			// 如果传入空部门,默认当前用户部门
			ff.setDepartment(nowuser.getStaff().getDepartment());

		}
		if (staff.getStatus() != null)
			ff.setStatus(staff.getStatus());
		if (staff.getStaffNo() != null)
			ff.setJobNo(staff.getStaffNo());
		if (staff.getPhoto() != null)
			ff.setPhoto(staff.getPhoto());
		if (staff.getPhone() != null)
			ff.setPhone(staff.getPhone());
		if (staff.getJobPosition() != null)
			ff.setJobPosition(staff.getJobPosition());
		if (staff.getLeadership() != null)
			ff.setLeadership(staff.getLeadership());
		if (staff.getEmail() != null)
			ff.setEmail(staff.getEmail());
		if (staff.getDob() != null)
			ff.setDob(staff.getDob());
		if (staff.getStaffName() != null)
			ff.setName(staff.getStaffName());

		// ff.setTxAccountId(staff.getTxAccountId());---放到激活账户在做

		if (StringUtil.isEmptyString(staff.getStaffId())) {
			// Create a new staff
			ff.setCorporation(nowuser.getCorporation());
			ff.setCreatedBy(nowuser);
			ff.setCreatedAt(DateUtil.getTime());
			ff.setDeleted(0);
			staffDao.save(ff);
		} else {
			ff.setId(staff.getStaffId());
			ff.setLastModifiedAt(DateUtil.getTime());
			ff.setLastModifiedBy(nowuser);

			staffDao.update(ff);
		}
		// 判断用户离职..
		if (staff.getStatus() == StaffStatus.DEPARTURE) {
			SysUser u = userDao.findUserByStaffId(ff.getId());
			if (u != null) {
				u.setStatus(UserStatus.Inactive);
				userDao.update(u);
			}
		} else if (staff.getStatus() == StaffStatus.JOB) {
			SysUser u = userDao.findUserByStaffId(ff.getId());
			if (u != null) {
				u.setStatus(UserStatus.Active);
				userDao.update(u);
			}
		}

		if (staff.getUserRoleVos() != null && staff.getUserRoleVos().size() > 0) {
			SysUser u = userDao.findUserByStaffId(ff.getId());
			if (u != null) {
				// 清除角色(除开用户,部门管理员)
				List<SysUserRole> lt = userRoleDao.findUserRoleByUserId(u
						.getId());
				if (lt.size() > 0) {
					for (SysUserRole r : lt) {
						if (r.getRole().getName() != UserRole.STAFF	)
							userRoleDao.delete(r);
					}
				}

				// 创建角色--
				for (UserRole role : staff.getUserRoleVos()) {
					SysUserRole userRole = new SysUserRole();
					userRole.setRole(roleDao.findRoleByRoleName(role));
					userRole.setCreatedBy(nowuser);
					userRole.setCreatedAt(DateUtil.getTime());
					userRole.setLastModifiedAt(DateUtil.getTime());
					userRole.setLastModifiedBy(nowuser);
					userRole.setUser(u);
					userRoleDao.createUserRole(userRole);
				}

			}
		} else if (staff.getUserRoleVos() != null) {
			SysUser u = userDao.findUserByStaffId(ff.getId());
			if (u != null) {
				// 清除角色(除开用户)
				List<SysUserRole> lt = userRoleDao.findUserRoleByUserId(u
						.getId());
				if (lt.size() > 0) {
					for (SysUserRole r : lt) {
						if (r.getRole().getName() != UserRole.STAFF)
							userRoleDao.delete(r);
					}
				}
			}
		}
		
		//判断是否修改用户名称
		SysUser u = userDao.findUserByStaffId(ff.getId());
		if(u!=null && ff.getName()!=null)
		{
			if (userDao.findUserByUserName(ff.getName()).size() > 0) {
				return "用户名已经存在";
			}
			else
			{
				u.setUserName(ff.getName());
				userDao.update(u);
			}
		}

		return ff.getId();
	}

	@Override
	public Staff findStaffById(String staffId) {
		return staffDao.findById(Staff.class, staffId);
	}

	@Override
	public GeneratedUserConstants generatedUserbyStaff(String staffId,
			UserContext context,String pwd) {
		Staff staff = staffDao.findById(Staff.class, staffId);
		SysUser user = userDao.findUserByStaffId(staff.getId());
		SysUser nowuser = userDao.findUserById(context.getUserId());

		if (user != null) {
			return GeneratedUserConstants.UsernamePresence;
		} else {
			String password = "";
			try {
				password = md5.MD5(pwd);// 初始密码
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// create user
			Date now = DateUtil.getTime();
			SysUser u = new SysUser();
			// check duplicate username
			String username = staff.getName();
			if (userDao.findUserByUserName(username).size() > 0) {
				return GeneratedUserConstants.UsernameRepeat;
			} else {
//				// 创建交易系统ID
//				String accountId = transactionService.createNewAccount();
//				staff.setTxAccountId(accountId);
//				staffDao.update(staff);

				// 创建用户
				u.setUserName(username);
				u.setPassword(password);
				u.setCorporation(staff.getCorporation());
				u.setCreatedAt(now);
				u.setCreatedBy(nowuser);
				u.setLastModifiedAt(now);
				u.setLastModifiedBy(nowuser);
				u.setStatus(UserStatus.Active);
				u.setStaff(staff);
				userDao.save(u);

				// 创建角色---员工

				SysUserRole userRole = new SysUserRole();
				userRole.setRole(roleDao.findRoleByRoleName(UserRole.STAFF));
				userRole.setCreatedBy(nowuser);
				userRole.setCreatedAt(DateUtil.getTime());
				userRole.setLastModifiedAt(DateUtil.getTime());
				userRole.setLastModifiedBy(nowuser);
				userRole.setUser(u);
				userRoleDao.createUserRole(userRole);

			}
		}
		return GeneratedUserConstants.Success;
	}

	public String createHrUser(StaffUserProcess staffProcess) {
		Corporation corporation = corporationLogic.findCorporationById(staffProcess.getCorpId());
		
//查找货添加顶级部门
		Department rootdepart = deptLogic.getRootDepartmentOfCorporation(corporation.getId());
		
		Staff ff = new Staff();
		// 默认当前用户部门为顶级部门Root_
		ff.setDepartment(rootdepart);
		ff.setStatus(StaffStatus.JOB);
		ff.setPhone(staffProcess.getTell());
		ff.setEmail(staffProcess.getEmail());
		ff.setName(staffProcess.getName());
//		String accountId = transactionService.createNewAccount();
//		ff.setTxAccountId(accountId);
		// Create a new staff

		ff.setCorporation(corporation);
		// ff.setCreatedBy(nowuser);
		ff.setCreatedAt(DateUtil.getTime());
		ff.setDeleted(0);
		Staff newstaff = staffDao.save(ff);

		// ==================创建用户
		String password = "";
		try {
			password = md5.MD5(staffProcess.getPassword());// 初始密码 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// create user
		Date now = DateUtil.getTime();
		SysUser user = new SysUser();
		user.setUserName(staffProcess.getUsername());
		user.setPassword(password);
		user.setCorporation(corporation);
		user.setCreatedAt(now);
		// u.setCreatedBy(nowuser);
		user.setLastModifiedAt(now);
		// u.setLastModifiedBy(nowuser);
		user.setStatus(UserStatus.Active);
		user.setStaff(newstaff);
		user = userDao.save(user);
//		// 初始化增加角色表数据
//		SysRole role1 = new SysRole();
//		role1.setName(UserRole.CORP_ADMIN);
//		roleDao.save(role1);
//		SysRole role2 = new SysRole();
//		role2.setName(UserRole.GIFT);
//		roleDao.save(role2);
//		SysRole role3 = new SysRole();
//		role3.setName(UserRole.DEPT_MGR);
//		roleDao.save(role3);
//		SysRole role4 = new SysRole();
//		role4.setName(UserRole.STAFF);
//		roleDao.save(role4);
		// 创建用户角色
		for (int i = 0; i < staffProcess.getRoles().size(); i++) {
			SysUserRole userRole = new SysUserRole();
			userRole.setRole(roleDao.findRoleByRoleName(staffProcess.getRoles()
					.get(i)));
			userRole.setCreatedBy(user);
			userRole.setCreatedAt(DateUtil.getTime());
			userRole.setLastModifiedAt(DateUtil.getTime());
			userRole.setLastModifiedBy(user);
			userRole.setUser(user);
			userRoleDao.createUserRole(userRole);

		}
		corporation.setStaff(newstaff);
		corporation.setIsCreateHrAccount(1);
		corporationDao.save(corporation);
		return user.getId();
	}

	public Staff updateLeadTime(String staffId, int leadTime) {
		Staff staff = staffDao.findById(Staff.class, staffId);
		staff.setLeadTime(leadTime);
		return staffDao.update(staff);
	}

	@Override
	public List<UserRole> findUserRoles(String staffId) {
		SysUser u = userDao.findUserByStaffId(staffId);
		if (u != null) {
			List<SysUserRole> userRole = userRoleDao.findUserRoleByUserId(u
					.getId());
			if (userRole != null && userRole.size() > 0) {
				List<UserRole> roles = new ArrayList<UserRole>();
				for (SysUserRole r : userRole) {
					roles.add(r.getRole().getName());
				}
				return roles;
			}
		}
		return null;
	}

	@Override
	public int deleteStaffById(UserContext context, String staffId) {
		try {
			SysUser user=null;
			if(context!=null)
				 user=userDao.findUserById(context.getUserId());
			Staff staff=staffDao.findById(Staff.class, staffId);
			staff.setDeleted(1);
			staff.setLastModifiedAt(new Date());
			staff.setLastModifiedBy(user);
			staffDao.update(staff);
			
			SysUser us=userDao.findUserByStaffId(staff.getId());
			if(us!=null)
			{
				us.setStatus(UserStatus.Inactive);
				us.setLastModifiedAt(new Date());
				us.setLastModifiedBy(user);
				userDao.update(us);
			}
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int enabledStaffById(UserContext context, String staffId) {
		try {
			SysUser user=null;
			if(context!=null)
				 user=userDao.findUserById(context.getUserId());
			Staff staff=staffDao.findById(Staff.class, staffId);
			staff.setDeleted(0);
			staff.setLastModifiedAt(new Date());
			staff.setLastModifiedBy(user);
			staffDao.update(staff);
			
			SysUser us=userDao.findUserByStaffId(staff.getId());
			if(us!=null)
			{
				us.setStatus(UserStatus.Active);
				us.setLastModifiedAt(new Date());
				us.setLastModifiedBy(user);
				userDao.update(us);
			}
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}


}
