package com.omdasoft.orderonline.dao.org;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.org.Corporation;

public class CorporationDao extends BaseDao<Corporation> {



	/**
	 * Find corporation which is matching subdomain name in a case-insensitive
	 * manner whenever its account is active or inactive.
	 * 
	 * @param subdomain
	 * @return
	 */
	public Corporation findBySubdomainCI(String subdomain) {
		String s = subdomain.toUpperCase();
		Query q = getEm()
				.createQuery(
						"SELECT corp FROM HrUser u INNER JOIN u.corporation corp WHERE UPPER(corp.subdomain) = :subdomain "
				// + " AND u.status = :userStatus"
				).setParameter("subdomain", s)
		// .setParameter("userStatus", UserStatus.Active)
		;
		try {
			return (Corporation) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Corporation> findCorporationByCRMEnterpirseId(
			String crmEnterPriserId) {
		return getEm()
				.createQuery(
						" FROM Corporation c WHERE c.corporationId=:crmEnterPriserId ")
				.setParameter("crmEnterPriserId", crmEnterPriserId)
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public int getCorp() {
		 int sum = 0;
		 List<Corporation> list = getEm().createQuery(" FROM Corporation c  ").getResultList();
		 if(list.size()>0)
			 sum=list.size();
		 return sum;
	}
	@SuppressWarnings("unchecked")
	public Corporation getDeCorp() {
		Corporation sum = null;
		 List<Corporation> list = getEm().createQuery(" FROM Corporation c  ").getResultList();
		 if(list.size()>0)
			 sum=list.get(0);
		 return sum;
	}
	@SuppressWarnings("unchecked")
	public String getCorporationBycId(String cid) {
		String corpid = null;
		 List<String> list = getEm().createQuery("SELECT c.id FROM Corporation c WHERE c.cid = :cid").setParameter("cid", cid).getResultList();
		 if(list.size()>0)
			 corpid=list.get(0);
		 return corpid;
	}
}
