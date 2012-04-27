package com.omdasoft.orderonline.dao.org;

import java.util.List;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.org.OrgInit;

public class OrgInitDao extends BaseDao<OrgInit> {
	@SuppressWarnings("unchecked")
	public OrgInit getOrgInit() {
		List<OrgInit> list =getEm().createQuery("FROM OrgInit m ").getResultList();
		OrgInit orgInit=null;
		if(list.size()>0){
			orgInit = list.get(0);
		}
		return orgInit;
	}
	
}
