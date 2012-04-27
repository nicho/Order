package com.omdasoft.orderonline.service.staff;

import java.util.List;

import com.omdasoft.orderonline.dao.org.StaffDao.QueryStaffPageActionResult;
import com.omdasoft.orderonline.domain.org.Staff;
import com.omdasoft.orderonline.model.staff.StaffProcess;
import com.omdasoft.orderonline.model.staff.StaffSearchCriteria;
import com.omdasoft.orderonline.model.staff.StaffUserProcess;
import com.omdasoft.orderonline.model.user.GeneratedUserConstants;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserRole;


public interface IStaffService {

	

	/**
	 * 查询员工列表
	 * @param criteria
	 * @param context
	 * @return
	 */
	public QueryStaffPageActionResult queryStaffList(StaffSearchCriteria criteria,UserContext context);
	/**
	 * 创建 and 修改..员工
	 * @param staffProcess,context
	 * @return
	 */
	public String createOrUpdateStaff(StaffProcess staff,UserContext context);
	/**
	 * 查询员工信息
	 * @param staffId
	 * @return
	 */
	public Staff findStaffById(String staffId);
	
	/**
	 * 删除员工
	 * @param staffId
	 * @return
	 */
	public int deleteStaffById(UserContext context,String staffId);
	
	/**
	 * 启用员工
	 * @param staffId
	 * @return
	 */
	public int enabledStaffById(UserContext context,String staffId);
	
	/**
	 * 根据员工生成账户
	 * @param staffId,context
	 * @return
	 */
	public GeneratedUserConstants generatedUserbyStaff(String staffId,UserContext context);
	
	public String createHrUser(StaffUserProcess staffProcess);	
	/**
	 * 查询员工信息
	 * @param staffId
	 * @return
	 */
	public Staff updateLeadTime(String staffId,int leadTime);
	
	/**
	 * 查询员工角色
	 * @param staffId
	 * @return
	 */
	public List<UserRole> findUserRoles(String staffId);
}
