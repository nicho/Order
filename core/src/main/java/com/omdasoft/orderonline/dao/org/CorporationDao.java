package com.omdasoft.orderonline.dao.org;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.model.user.UserStatus;
import com.omdasoft.orderonline.model.vo.CompanySearchVo;

public class CorporationDao extends BaseDao<Corporation> {
	
	public class QueryCompanyPageActionResult {

		private int total;

		private List<Corporation> resultList;

		/**
		 * @return the total
		 */
		public int getTotal() {
			return total;
		}

		/**
		 * @param total
		 *            the total to set
		 */
		public void setTotal(int total) {
			this.total = total;
		}

		/**
		 * @return the resultList
		 */
		public List<Corporation> getResultList() {
			return resultList;
		}

		/**
		 * @param resultList
		 *            the resultList to set
		 */
		public void setResultList(List<Corporation> resultList) {
			this.resultList = resultList;
		}

	}
	
	/**
	 * Search corporation with case-insensitive matching of corporation
	 * sub-domain name and with any matching HrUser records with matching user
	 * status.
	 * 
	 * @param subdomain
	 * @param userStatus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Corporation> findBySubdomainHrUserStatus(String subdomain,
			UserStatus userStatus) {

		Query q = getEm()
				.createQuery(
						"SELECT DISTINCT corp FROM HrUser u INNER JOIN u.corporation corp WHERE UPPER(corp.subdomain) = :subdomain"
								+ (userStatus == null ? ""
										: " AND u.status = :userStatus"));
		q.setParameter("subdomain", subdomain.toUpperCase());
		if (userStatus != null)
			q.setParameter("userStatus", userStatus);
		return q.getResultList();

	}

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
		List<Corporation> list = getEm().createQuery(" FROM Corporation c  ")
				.getResultList();
		if (list.size() > 0)
			sum = list.size();
		return sum;
	}

	@SuppressWarnings("unchecked")
	public List<Corporation> getWholeCorporations() {
		return getEm().createQuery(" FROM Corporation c  ").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Corporation> findCorporationByEnterpirseName(
			CompanySearchVo companySearchVo) {
		StringBuffer hql = new StringBuffer();
		hql.append(" FROM Corporation cor WHERE 1=1 ");
		hql.append(" and cor.name LIKE '%"+companySearchVo.getName()+"%' ");
		
		if (companySearchVo.getSortingDetail() != null
				&& companySearchVo.getSortingDetail().getSort() != null
				&& companySearchVo.getSortingDetail().getDirection() != null) {
			hql.append(" ORDER BY cor."
					+ companySearchVo.getSortingDetail().getSort() + " "
					+ companySearchVo.getSortingDetail().getDirection());
		}
		
		Query query = getEm().createQuery(hql.toString());
		
		if (companySearchVo.getPaginationDetail() != null && companySearchVo.getPaginationDetail().getLimit()!=0 ) {
			int limit = companySearchVo.getPaginationDetail().getLimit();
			int start = companySearchVo.getPaginationDetail().getStart();

			logger.debug("pagination - start{}, limit:{}", new Object[] {
					start, limit });

			query.setMaxResults(limit);
			query.setFirstResult(start);
		}
		
		return query.getResultList();
	}
	
	public int findCorporationCountByEnterpirseName(
			CompanySearchVo companySearchVo) {
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT COUNT(cor) FROM Corporation cor WHERE 1=1 ");
		hql.append(" and cor.name LIKE '%"+companySearchVo.getName()+"%' ");
		
		Query query = getEm().createQuery(hql.toString());
		
		return Integer.parseInt(query.getSingleResult().toString());
	}
	
	public QueryCompanyPageActionResult queryCompanyPageAction(
			CompanySearchVo companySearchVo) {

		QueryCompanyPageActionResult result = new QueryCompanyPageActionResult();

		result.setResultList(findCorporationByEnterpirseName(companySearchVo));
		result.setTotal(findCorporationCountByEnterpirseName(companySearchVo));

		return result;
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
