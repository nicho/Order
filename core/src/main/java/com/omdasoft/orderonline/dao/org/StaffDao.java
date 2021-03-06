package com.omdasoft.orderonline.dao.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Query;

import org.apache.commons.beanutils.BeanUtils;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.org.Staff;
import com.omdasoft.orderonline.model.staff.StaffSearchVo;
import com.omdasoft.orderonline.model.staff.StaffSearchVo.MultipleIdParam;
import com.omdasoft.orderonline.model.staff.StaffSearchVo.OneIdParam;
import com.omdasoft.orderonline.util.StringUtil;

/**
 * Dao of {@link Staff}
 * 
 * @author yanxin
 * @since 1.0
 */
public class StaffDao extends BaseDao<Staff> {
	public class QueryStaffPageActionResult {

		private int total;

		private List<Staff> resultList;

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
		public List<Staff> getResultList() {
			return resultList;
		}

		/**
		 * @param resultList
		 *            the resultList to set
		 */
		public void setResultList(List<Staff> resultList) {
			this.resultList = resultList;
		}

	}
	
	
	/**
	 * Find list of staff by id of department.
	 * 
	 * @param deptId
	 */
	@SuppressWarnings("unchecked")
	public List<Staff> findStaffsByDepartmentId(String deptId) {
		return getEm()
				.createQuery("FROM Staff s WHERE s.department.id =:deptId")
				.setParameter("deptId", deptId).getResultList();
	}

	/**
	 * Find list of staff by corporation id and index about lft/rgt.
	 * 
	 * @param corpId
	 * @param lft
	 * @param rgt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Staff> findStaffsByDepartmentLftRgt(String corpId, int lft,
			int rgt) {
		logger.debug(
				"Invoking method findStaffsByDepartmentLftRgt, param[corpId={}, lft={}, rgt={}]",
				new Object[] { corpId, lft, rgt });
		return getEm()
				.createQuery(
						"FROM Staff s WHERE s.corporation.id =:corpId AND s.department.lft = :lft AND s.department.rgt = :rgt")
				.setParameter("corpId", corpId).setParameter("lft", lft)
				.setParameter("rgt", rgt).getResultList();
	}
	
	public QueryStaffPageActionResult queryStaffPageAction(	StaffSearchVo searchVo) {

		logger.debug(" Process in queryStaffPageAction, searchVo:{}", searchVo);

		StaffSearchVo finalVo = searchVo;

		if (searchVo.getDeptParam() != null) {
			if (searchVo.getDeptParam() instanceof OneIdParam) {
				OneIdParam param = (OneIdParam) searchVo.getDeptParam();
				// convert to department IDs
				Set<String> deptIds = findSiblingIds(param.getDeptId(), true);
				try {
					finalVo = (StaffSearchVo) BeanUtils.cloneBean(searchVo);
				} catch (Exception e) {
					logger.error("clone bean error.", e);
				}
				finalVo.setDeptParam(new MultipleIdParam(deptIds));
			}
		}
		logger.debug("finalVo:{}", finalVo.toString());

		QueryStaffPageActionResult result = new QueryStaffPageActionResult();

		result.setResultList(queryStaffPageActionData(finalVo));
		result.setTotal(queryStaffPageActionCount(finalVo));

		return result;
	}
   
	@SuppressWarnings("unchecked")
	private List<Staff> queryStaffPageActionData(StaffSearchVo searchVo) {
		return getStaffQuery(searchVo, SEARCH).getResultList();
	}

	public int queryStaffPageActionCount(StaffSearchVo searchVo) {
		return Integer.parseInt(getStaffQuery(searchVo, COUNT)
				.getSingleResult().toString());
	}

	private Query getStaffQuery(StaffSearchVo searchVo, String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		if (SEARCH.equals(type)) {
			hql.append(" SELECT staff FROM Staff staff WHERE 1=1 ");
		} else if (COUNT.equals(type)) {
			hql.append(" SELECT COUNT(staff) FROM Staff staff WHERE 1=1 ");
		}
		// 关键字 姓名/编号
		if (!StringUtil.isEmptyString(searchVo.getKeywords())) {
			hql.append(" AND (UPPER(staff.name) LIKE :keywords "
					// + " OR UPPER(staff.department.name) LIKE :keywords "
					//+ " OR UPPER(staff.email) LIKE :keywords "
					+ " OR UPPER(staff.jobNo) LIKE :keywords "
				//	+ " OR UPPER(staff.memberCardNumber) LIKE :keywords)"
					+") ");
			param.put("keywords", "%"
					+ searchVo.getKeywords().trim().toUpperCase() + "%");
		}
		if (!StringUtil.isEmptyString(searchVo.getDeptId())) {
			hql.append(" AND staff.department.id = :deptId ");
			param.put("deptId", searchVo.getDeptId());
		}
		if (searchVo.getStatus()!=null) {
			hql.append(" AND staff.status = :status ");
			param.put("status", searchVo.getStatus());
		}	
		if (searchVo.getEnterpriseId() != null) {
			hql.append(" AND staff.corporation.id =:corporationId");
			param.put("corporationId", searchVo.getEnterpriseId());
		}
		if (searchVo.getStaffids() != null && searchVo.getStaffids().size()>0) {
			hql.append(" AND staff.id IN (:staffids)");
			param.put("staffids", searchVo.getStaffids());
		}
		// department
		if (searchVo.getDeptParam() != null) {

			if (searchVo.getDeptParam() instanceof OneIdParam) {
				hql.append(" AND staff.department.id = :deptId ");
				OneIdParam idParam = (OneIdParam) searchVo.getDeptParam();
				param.put("deptId", idParam.getDeptId());
			} else if (searchVo.getDeptParam() instanceof MultipleIdParam) {
				hql.append(" AND staff.department.id IN (:deptIds) ");
				MultipleIdParam idParam = (MultipleIdParam) searchVo
						.getDeptParam();
				param.put("deptIds", idParam.getIds());
			}

		}
		hql.append(" AND staff.deleted = :deleted ");
		param.put("deleted", 0);
	
		// ORDER BY
		if (SEARCH.equals(type)) {
			if (searchVo!=null && searchVo.getSortingDetail() != null && searchVo.getSortingDetail().getSort() != null && searchVo.getSortingDetail().getDirection() != null) {
				hql.append(" ORDER BY staff."
						+ searchVo.getSortingDetail().getSort() + " "
						+ searchVo.getSortingDetail().getDirection());
			} else {
				hql.append(" ORDER BY staff.entryDate DESC ");
			}
		}
		logger.debug(" HQL:{} ", hql);
		Query query = getEm().createQuery(hql.toString());
		if (SEARCH.equals(type)) {
			if (searchVo!=null && searchVo.getPaginationDetail() != null) {
				int limit = searchVo.getPaginationDetail().getLimit();
				int start = searchVo.getPaginationDetail().getStart();

				logger.debug("pagination - start{}, limit:{}", new Object[] {
						start, limit });

				query.setMaxResults(limit);
				query.setFirstResult(start);
			}
		}
		if (param.size() > 0) {
			Set<String> key = param.keySet();
			for (String s : key) {
				query.setParameter(s, param.get(s));
			}
		}
		return query;
	}

	
	
	@SuppressWarnings("unchecked")
	public Set<String> findSiblingIds(String rootId, boolean includeRoot) {

		logger.debug(" Process in findSiblingIds, rootId:{}, includeRoot:{}",
				new Object[] { rootId, includeRoot });

		Set<String> result = new TreeSet<String>();
		List<String> currectList = new ArrayList<String>();

		currectList.add(rootId);

		StringBuffer eql = new StringBuffer();
		eql.append(" SELECT dept.id FROM Department dept WHERE dept.parent.id IN (:parentIds) ");
		logger.debug(" EQL:{}", eql.toString());

		while (true) {
			Query query = getEm().createQuery(eql.toString());
			currectList = query.setParameter("parentIds", currectList)
					.getResultList();
			logger.trace("currectList:{}", currectList);
			if (currectList == null || currectList.size() == 0) {
				break;
			} else {
				result.addAll(currectList);
			}
		}

		if (includeRoot) {
			result.add(rootId);
		}

		return result;
	}
	
	/**
	 * Find list of staff by ids
	 * 
	 * @param deptId
	 */
	@SuppressWarnings("unchecked")
	public List<Staff> findStaffsByStaffIds(List<String> staffIds) {
		return getEm()
				.createQuery("FROM Staff s WHERE s.id IN (:staffIds)")
				.setParameter("staffIds", staffIds).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Staff> findStaffsByCorporationId(String corporationId) {
		return getEm()
				.createQuery("FROM Staff s WHERE s.corporation.id = :corporationId")
				.setParameter("corporationId", corporationId).getResultList();
	}
}
