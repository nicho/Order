package com.omdasoft.orderonline.dao.user;

import java.util.List;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.user.SysRole;
import com.omdasoft.orderonline.model.user.UserRole;

public class RoleDao extends BaseDao<SysRole> {


	@SuppressWarnings("unchecked")
	public SysRole findRoleByRoleName(UserRole roleName) {
		
		List<SysRole> rolelt=getEm().createQuery("FROM SysRole u WHERE u.name = :roleName").setParameter("roleName", roleName).getResultList();
		if(rolelt.size()>0)
		return rolelt.get(0);
		else
		return null;
	}
	


	

	
}
