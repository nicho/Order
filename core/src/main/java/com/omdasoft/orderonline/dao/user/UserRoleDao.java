package com.omdasoft.orderonline.dao.user;

import java.util.List;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.user.SysUserRole;
import com.omdasoft.orderonline.model.user.UserRole;

public class UserRoleDao extends BaseDao<SysUserRole> {

	@SuppressWarnings("unchecked")
	public List<SysUserRole> findUserRoleByUserId(String userId) {
		return getEm()
				.createQuery("FROM SysUserRole u WHERE u.user.id = :userId")
				.setParameter("userId", userId).getResultList();
	}
	
	public String createUserRole(SysUserRole userRole) {
		this.save(userRole);	
		return userRole.getId();
	}

	@SuppressWarnings("unchecked")
	public List<String> findStaffIdsByRole(UserRole UserRoleName) {
		return getEm()
				.createQuery("SELECT u.user.staff.id FROM SysUserRole u WHERE u.role.name = :UserRoleName")
				.setParameter("UserRoleName", UserRoleName).getResultList();
	}

	
}
