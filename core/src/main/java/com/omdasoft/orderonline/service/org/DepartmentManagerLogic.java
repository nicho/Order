/**
 * 
 */
package com.omdasoft.orderonline.service.org;

import java.util.List;

import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.domain.org.Staff;
import com.omdasoft.orderonline.domain.org.manager.DepartmentManager;

/**
 * @author Cream
 * @since 0.2.0
 */
public interface DepartmentManagerLogic {

	/**
	 * 
	 * @return
	 */
	public List<String> findDepartmentIdsManagedByLoginUser();

	/**
	 * @param deptId
	 * @param staffIds
	 * @return
	 */
	public List<DepartmentManager> createManager(String deptId, List<String> staffIds);
	
	public void deleteManager(String deptId, List<String> staffIds);

	/**
	 * @param deptId
	 * @return
	 */
	public List<Staff> findManagersByDepartmentId(String deptId);

	/**
	 * @param staffId
	 * @return
	 */
	public List<Department> findDepartmentsManagedByStaffId(String staffId);
}
