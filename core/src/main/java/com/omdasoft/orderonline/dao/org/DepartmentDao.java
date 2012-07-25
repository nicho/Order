package com.omdasoft.orderonline.dao.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.user.RestaurantSearchCriteria;
import com.omdasoft.orderonline.util.DateUtil;
import com.omdasoft.orderonline.util.StringUtil;

/**
 * Dao of {@link Department}
 * 
 * @author yanxin
 * @since 1.0
 */
public class DepartmentDao extends BaseDao<Department> {

	public static final String ROOT_NAME = "ROOT_DEPT";
	public PageStore<Department> queryRestaurantPageAction(RestaurantSearchCriteria criteria) {

		PageStore<Department> result = new PageStore<Department>();

		result.setResultList(this.dishesList(criteria));
		result.setResultCount(this.countuser(criteria));

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Department> dishesList(RestaurantSearchCriteria criteria) {
		List<Department> result = new ArrayList<Department>();

		Query query = getFetchDepartmentQuery(SEARCH, criteria);

		result = query.getResultList();

		return result;
	}

	public int countuser(RestaurantSearchCriteria criteria) {

		int count = 0;
		Query query = getFetchDepartmentQuery(COUNT, criteria);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by user method, result count : {}", count);
		return count;
	}

	private Query getFetchDepartmentQuery(String type, RestaurantSearchCriteria vo) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT o FROM Department o  where  1=1 AND o.parent is not null AND (o.deleted=0 or o.deleted is null) ");
		
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(o) FROM Department o where  1=1 AND o.parent is not null AND (o.deleted=0 or o.deleted is null) ");

		}
		eql.append(" AND o.corporation.id = :corpId");
		param.put("corpId", vo.getCorporationId());
		if (SEARCH.equals(type)) {
			if (vo!=null && vo.getSortingDetail() != null && vo.getSortingDetail().getSort()!=null && vo.getSortingDetail().getDirection()!=null) {
				eql.append(" user BY o." + vo.getSortingDetail().getSort()
						+ " " + vo.getSortingDetail().getDirection());
			}
		}
		System.out.println("EQL : " + eql);
		Query query = getEm().createQuery(eql.toString());
		if (SEARCH.equals(type)) {
			if (vo!=null && vo.getPaginationDetail() != null
					&& vo.getPaginationDetail().getLimit() != 0) {
				int start = vo.getPaginationDetail().getStart();
				int limit = vo.getPaginationDetail().getLimit();

				query.setFirstResult(start);
				query.setMaxResults(limit);
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
	/**
	 * Get the root Department.
	 * 
	 * @return
	 */
	public Department getRootDepartmentOfCorp(String corporationId) {
		String name = ROOT_NAME + corporationId;
		try {
			Department dept = (Department) getEm()
					.createQuery(
							"FROM Department dept WHERE dept.name = :name AND dept.corporation.id = :corpId")
					.setParameter("name", name)
					.setParameter("corpId", corporationId).getSingleResult();
			return dept;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Department addRootDepartment(Corporation corp) {
		String name = ROOT_NAME + corp.getId();
		Date now = DateUtil.getTime();
		Department dept = new Department();
		dept.setCorporation(corp);
	    dept.setName(name);
		dept.setLft(1);
		dept.setRgt(2);
		dept.setCreatedAt(now);
		getEm().persist(dept);

		return dept;
	}

	/**
	 * Maintain index of Department after adding a new Department node.Every
	 * corporation maintain a series of independent index.
	 * 
	 * @param index
	 */
	public void maintainIndexAfterAddNode(int index, String corpId) {
		logger.debug(
				"Invoking method maintainIndexAfterAddNode, param[index={}, corpId={}]",
				new Object[] { index, corpId });
		getEm().createQuery(
				"UPDATE Department d SET d.lft = (d.lft+2) WHERE d.lft >= :index AND d.corporation.id =:corpId")
				.setParameter("index", index).setParameter("corpId", corpId)
				.executeUpdate();
		getEm().createQuery(
				"UPDATE Department d SET d.rgt = (d.rgt+2) WHERE d.rgt >= :index AND d.corporation.id =:corpId")
				.setParameter("index", index).setParameter("corpId", corpId)
				.executeUpdate();
	}

	/**
	 * Maintain index of Department after a leaf Department node.Every
	 * corporation maintain a series of independent index.
	 * 
	 * @param index
	 */
	public void maintainIndexAfterDeleteNode(int index, String corpId) {
		getEm().createQuery(
				"UPDATE Department d SET d.lft = (d.lft-2) WHERE d.lft >= :index AND d.corporation.id =:corpId")
				.setParameter("index", index).setParameter("corpId", corpId)
				.executeUpdate();
		getEm().createQuery(
				"UPDATE Department d SET d.rgt = (d.rgt-2) WHERE d.rgt >= :index AND d.corporation.id =:corpId")
				.setParameter("index", index).setParameter("corpId", corpId)
				.executeUpdate();
	}

	/**
	 * Find list of department by parent id.
	 * 
	 * @param deptId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> findDepartmentsByParentId(String deptId) {
		return getEm()
				.createQuery("FROM Department d WHERE d.parent.id =:deptId")
				.setParameter("deptId", deptId).getResultList();
	}

	/**
	 * Find list of department by corporation id. It should not contain root
	 * department of the corporation.
	 * 
	 * @param deptId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> findDepartmentsByCoporationId(String corporationId) {
		return getEm()
				.createQuery(
						"FROM Department d WHERE d.corporation.id =:corpId ")
				.setParameter("corpId", corporationId).getResultList();
	}

	/**
	 * Find list of department by parent id and corporation id.
	 * 
	 * @param deptId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> findDepartmentsByParentIdAndCoporationId(
			String deptId, String corporationId) {
		return getEm()
				.createQuery(
						"FROM Department d WHERE d.parent.id =:deptId AND d.corporation.id =:corpId")
				.setParameter("deptId", deptId)
				.setParameter("corpId", corporationId).getResultList();
	}



	/**
	 * Find list of department by index(lft and rgt).
	 * 
	 * @param lft
	 * @param rgt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> findDepartmentsByLefRgt(int lft, int rgt) {
		return getEm()
				.createQuery(
						"FROM Department d WHERE d.lft > :lft AND d.rgt < :rgt")
				.setParameter("lft", lft).setParameter("rgt", rgt)
				.getResultList();
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
	 * @param departmentIds
	 * @return
	 */
	public String mergeDepartment(String departmentIds,String departmentName,String leaderId) {
		
		

		
		return "";
	}

	@SuppressWarnings("unchecked")
	public List<Department> getDepartmentsOfCorporationAndKey(
			String corporationId, String name) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT d FROM Department d WHERE d.parent is not null  AND (d.deleted=0 or d.deleted is null) ");
		if (!StringUtil.isEmptyString(corporationId)) {
			hql.append(" AND d.corporation.id =:corpId  ");
			param.put("corpId", corporationId);
		}
		if (!StringUtil.isEmptyString(name)) {
			hql.append(" AND d.name LIKE :name  ");
			param.put("name", "%" + name + "%");
		}
		Query query = getEm().createQuery(hql.toString());
		for (String key : param.keySet()) {
			query.setParameter(key, param.get(key));
		}

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getDepartmentCityName(String corporationId) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT distinct d.city FROM Department d WHERE d.parent is not null  AND (d.deleted=0 or d.deleted is null) ");
		if (!StringUtil.isEmptyString(corporationId)) {
			hql.append(" AND d.corporation.id =:corpId  ");
			param.put("corpId", corporationId);
		}
		
		Query query = getEm().createQuery(hql.toString());
		for (String key : param.keySet()) {
			query.setParameter(key, param.get(key));
		}

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Department> getDepartmentByCityName(String corporationId,String cityName) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT d FROM Department d WHERE d.parent is not null  AND (d.deleted=0 or d.deleted is null) ");
		if (!StringUtil.isEmptyString(corporationId)) {
			hql.append(" AND d.corporation.id =:corpId  ");
			param.put("corpId", corporationId);
		}
		if (!StringUtil.isEmptyString(cityName)) {
			hql.append(" AND d.city =:cityName  ");
			param.put("cityName", cityName);
		}
		Query query = getEm().createQuery(hql.toString());
		for (String key : param.keySet()) {
			query.setParameter(key, param.get(key));
		}

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public String getDepartmentBydId(String did) {
		String deptid = null;
		 List<String> list = getEm().createQuery("SELECT c.id FROM Department c WHERE c.did = :did").setParameter("did", did).getResultList();
		 if(list.size()>0)
			 deptid=list.get(0);
		 return deptid;
	}
	
	public Department findDepartmentByAdminUserId(String userId) {
		try {
			Department dept = (Department) getEm()
					.createQuery(
							"FROM Department dept WHERE dept.deptAdmin.id = :userId ")
					.setParameter("userId", userId).getSingleResult();
			return dept;
		} catch (NoResultException e) {
			return null;
		}
	}
}
