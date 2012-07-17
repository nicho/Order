package com.omdasoft.orderonline.service.org;

import java.util.List;

import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.domain.org.Staff;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.org.exception.DepartmentDeleteException;
import com.omdasoft.orderonline.model.org.search.DepartmentListVo;
import com.omdasoft.orderonline.model.org.search.DepartmentManageVo;
import com.omdasoft.orderonline.model.user.UserContext;

/**
 * Service of corporation.
 * 
 * @author yanrui
 * @since 1.5
 */
public interface DepartmentService {

	/**
	 * 保存
	 * @param context
	 * @param department
	 * @return
	 */
	public Department save(UserContext context, Department department);

	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public Department findDepartmentById(String id);
	/**
	 * 删除部门根据ID
	 * @param id
	 * @return
	 * @throws DepartmentDeleteException 
	 */
	public String deleteDepartment(String id) throws DepartmentDeleteException;
	/**
	 * 部门列表
	 * @param context
	 * @param department
	 * @return
	 */
	public PageStore<Department> departmentList(UserContext context,DepartmentListVo departmentListVo);

	/**
	 * @param corporationId
	 * @return
	 */
	public List<DepartmentManageVo> getDepartmentManageList(String corporationId);
	
	public List<DepartmentManageVo> getDepartmentLeaderList(String leaderId,String corporcationId);

	/**
	 * @param uc
	 * @param department
	 * @return
	 */
	public Department saveDepartment(UserContext uc, Department department,List<String> staffIds,List<String> preLeaderIds);

	/**
	 * @param corpId
	 * @return
	 */
	public Department getRootDepartmentOfCorporation(String corpId);

	/**
	 * @param uc
	 * @param departmentIds
	 * @return
	 */
	public String mergeDepartment(UserContext uc, String departmentIds,String departmentName,String leaderId);

	/**
	 *获取部门.查询.key(name).去掉根部门
	 * 
	 * @param corporationId
	 * @return
	 */
	public List<Department> getDepartmentsOfCorporationAndKey(String corporationId,String key);

	
	/**
	 * @param deptId
	 * @param containItSelf
	 * @return
	 */
	public List<Department> getWholeChildren(String deptId, boolean containItSelf);

	/**
	 * @param deptId
	 * @param containItSelf
	 * @return
	 */
	public List<String> getWholeChildrenNames(String deptId, boolean containItSelf);


	public List<Staff> findManagersByDepartmentId(String deptId);
	
	public List<Department> findDepartmentsManagedByStaffId(String staffId);

	/**
	 * @param corporationId
	 * @return
	 */
	public List<Department> getWholeDepartmentsOfCorporation(String corporationId);
	public List<String> getDepartmentCityName(String corporationId);
	public List<String[]> getDepartmentByCityName(String corporationId,String cityName);
	/**
	 * 根据DID查找分店Id
	 * @param id
	 * @return
	 */
	public String findDepartmentBydId(String did);

}
