package com.omdasoft.orderonline.dao.dishes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.dishes.DishesType;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.dishes.DishesTypeSearchCriteria;
import com.omdasoft.orderonline.util.StringUtil;

public class DishesTypeDao extends BaseDao<DishesType> {

	public PageStore<DishesType> queryDishesTypePageAction(DishesTypeSearchCriteria criteria) {

		PageStore<DishesType> result = new PageStore<DishesType>();

		result.setResultList(this.dishesList(criteria));
		result.setResultCount(this.countuser(criteria));

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<DishesType> dishesList(DishesTypeSearchCriteria criteria) {
		List<DishesType> result = new ArrayList<DishesType>();

		Query query = getFetchDishesTypeQuery(SEARCH, criteria);

		result = query.getResultList();

		return result;
	}

	public int countuser(DishesTypeSearchCriteria criteria) {

		int count = 0;
		Query query = getFetchDishesTypeQuery(COUNT, criteria);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by user method, result count : {}", count);
		return count;
	}

	private Query getFetchDishesTypeQuery(String type, DishesTypeSearchCriteria vo) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT o FROM DishesType o  where  1=1 AND (o.deleted=0 or o.deleted is null) ");
		
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(o) FROM DishesType o where  1=1 AND (o.deleted=0 or o.deleted is null) ");

		}
		if(!StringUtil.isEmptyString(vo.getCorporationId()))
		{
			eql.append(" AND o.corporation.id = :corpId ");
			param.put("corpId", vo.getCorporationId());

		}

		if(!StringUtil.isEmptyString(vo.getKeyName()))
		{
			eql.append(" AND o.name LIKE :keyName ");
			param.put("keyName", "%"+vo.getKeyName()+"%");

		}
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

	public DishesType findDishesTypeByrId(String rid,String corpId) {
		try {
			return  (DishesType) getEm()
					.createQuery("FROM DishesType s WHERE s.rid = :rid AND s.corporation.id=:corpId ")
					.setParameter("rid", rid).setParameter("corpId", corpId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<String> findDishesTypePanel() {
		try {
			return  getEm()
					.createQuery("SELECT distinct s.dishesType FROM DishesType s ")
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public int deleteDishesTypeBydeptId(String deptId) {
		try {
			return  getEm()	.createQuery("UPDATE FROM DishesType s set s.deleted=1 WHERE s.department.id = :deptId")
					.setParameter("deptId", deptId).executeUpdate();
		} catch (NoResultException e) {
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DishesType> findDishesTypeBydeptId(String deptId) {
		try {
			return  getEm()	.createQuery(" FROM DishesType s WHERE s.department.id = :deptId AND (s.deleted=0 or s.deleted is null)")
					.setParameter("deptId", deptId).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
