package com.omdasoft.orderonline.dao.dishes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.dishes.Dishes;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.dishes.DishesSearchCriteria;
import com.omdasoft.orderonline.util.StringUtil;

public class DishesDao extends BaseDao<Dishes> {

	public Dishes findDishesById(String id) {
		Dishes dishes = (Dishes) getEm()
				.createQuery("FROM Dishes u WHERE u.id = :id")
				.setParameter("id", id).getSingleResult();
		return dishes;
	}

	public PageStore<Dishes> queryDishesPageAction(DishesSearchCriteria criteria) {

		PageStore<Dishes> result = new PageStore<Dishes>();

		result.setResultList(this.dishesList(criteria));
		result.setResultCount(this.countuser(criteria));

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Dishes> dishesList(DishesSearchCriteria criteria) {
		List<Dishes> result = new ArrayList<Dishes>();

		Query query = getFetchDishesQuery(SEARCH, criteria);

		result = query.getResultList();

		return result;
	}

	public int countuser(DishesSearchCriteria criteria) {

		int count = 0;
		Query query = getFetchDishesQuery(COUNT, criteria);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by user method, result count : {}", count);
		return count;
	}

	private Query getFetchDishesQuery(String type, DishesSearchCriteria vo) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT o FROM Dishes o  where  1=1 AND (o.deleted=0 or o.deleted is null) ");

		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(o) FROM Dishes o where  1=1 AND (o.deleted=0 or o.deleted is null) ");

		}
		if(vo!=null)
		{
			if (!StringUtil.isEmptyString(vo.getDishesTypeId())) {
				eql.append(" AND o.dishesType.id = :dishesType");
				param.put("dishesType", vo.getDishesTypeId());
			}
		
			if(!StringUtil.isEmptyString(vo.getCorporationId()))
			{
				eql.append(" AND o.corporation.id = :corpId ");
				param.put("corpId", vo.getCorporationId());
	
			}
			if(!StringUtil.isEmptyString(vo.getDeptId()))
			{
				eql.append(" AND o.department.id = :departmentId ");
				param.put("departmentId", vo.getDeptId());

			}
			
			if(!StringUtil.isEmptyString(vo.getKeyName()))
			{
				eql.append(" AND o.name LIKE :keyName ");
				param.put("keyName", "%"+vo.getKeyName()+"%");
	
			}
		}
		if (SEARCH.equals(type)) {
			if (vo!=null && vo.getSortingDetail() != null
					&& vo.getSortingDetail().getSort() != null
					&& vo.getSortingDetail().getDirection() != null) {
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
	public Dishes findDishesByrId(String rid,String cid) {
		try {
			return  (Dishes) getEm()
					.createQuery("FROM Dishes s WHERE s.rid = :rid AND s.corporation.id=:cid")
					.setParameter("rid", rid).setParameter("cid", cid).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	public int deleteDishesBydeptId(String deptId) {
		try {
			return  getEm()	.createQuery("UPDATE FROM Dishes s set s.deleted=1 WHERE s.department.id = :deptId")
					.setParameter("deptId", deptId).executeUpdate();
		} catch (NoResultException e) {
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Dishes> findDishesBydeptId(String deptId) {
		try {
			return  getEm()	.createQuery(" FROM Dishes s WHERE s.department.id = :deptId AND (s.deleted=0 or s.deleted is null)")
					.setParameter("deptId", deptId).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
