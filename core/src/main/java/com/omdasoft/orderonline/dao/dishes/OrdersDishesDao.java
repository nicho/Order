package com.omdasoft.orderonline.dao.dishes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.order.OrdersDishes;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.dishes.OrderDishesSearchCriteria;
import com.omdasoft.orderonline.util.StringUtil;

public class OrdersDishesDao extends BaseDao<OrdersDishes>{
	@SuppressWarnings("unchecked")
	public List<OrdersDishes> findOrdersDishesByOrderId(String orderId) {
		try {
			return  getEm()
					.createQuery("FROM OrdersDishes s WHERE s.orders.id = :orderId")
					.setParameter("orderId", orderId).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public PageStore<OrdersDishes> queryOrdersDishesPageAction(OrderDishesSearchCriteria criteria) {

		PageStore<OrdersDishes> result = new PageStore<OrdersDishes>();

		result.setResultList(this.dishesList(criteria));
		result.setResultCount(this.countuser(criteria));

		return result;
	}

	@SuppressWarnings("unchecked")
	private List<OrdersDishes> dishesList(OrderDishesSearchCriteria criteria) {
		List<OrdersDishes> result = new ArrayList<OrdersDishes>();

		Query query = getFetchOrdersDishesQuery(SEARCH, criteria);

		result = query.getResultList();

		return result;
	}

	private int countuser(OrderDishesSearchCriteria criteria) {

		int count = 0;
		Query query = getFetchOrdersDishesQuery(COUNT, criteria);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by user method, result count : {}", count);
		return count;
	}

	private Query getFetchOrdersDishesQuery(String type, OrderDishesSearchCriteria vo) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT o FROM OrdersDishes o  where  1=1 ");
		
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(o) FROM OrdersDishes o where  1=1 ");

		}
		if(!StringUtil.isEmptyString(vo.getOrderId()))
		{
			eql.append(" AND o.orders.id = :orderId");
			param.put("orderId", vo.getOrderId());
		}
		else
		{
			eql.append(" AND o.orders.id = :orderId");
			param.put("orderId", "notOrderId");
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
	
}
