package com.omdasoft.orderonline.dao.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.org.Restaurant;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.user.RestaurantSearchCriteria;

public class RestaurantDao extends BaseDao<Restaurant> {

	
	public Restaurant findRestaurantById(String id) {
		Restaurant dishes = (Restaurant) getEm().createQuery("FROM Restaurant u WHERE u.id = :id").setParameter("id", id).getSingleResult();
		return dishes;
	}
	public PageStore<Restaurant> queryRestaurantPageAction(RestaurantSearchCriteria criteria) {

		PageStore<Restaurant> result = new PageStore<Restaurant>();

		result.setResultList(this.dishesList(criteria));
		result.setResultCount(this.countuser(criteria));

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Restaurant> dishesList(RestaurantSearchCriteria criteria) {
		List<Restaurant> result = new ArrayList<Restaurant>();

		Query query = getFetchRestaurantQuery(SEARCH, criteria);

		result = query.getResultList();

		return result;
	}

	public int countuser(RestaurantSearchCriteria criteria) {

		int count = 0;
		Query query = getFetchRestaurantQuery(COUNT, criteria);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by user method, result count : {}", count);
		return count;
	}

	private Query getFetchRestaurantQuery(String type, RestaurantSearchCriteria vo) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT o FROM Restaurant o  where  1=1 ");
		
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(o) FROM Restaurant o where  1=1 ");

		}

		if (SEARCH.equals(type)) {
			if (vo.getSortingDetail() != null && vo.getSortingDetail().getSort()!=null && vo.getSortingDetail().getDirection()!=null) {
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
	
}
