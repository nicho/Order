package com.omdasoft.orderonline.dao.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.order.Orders;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.order.OrderListCriteria;
import com.omdasoft.orderonline.util.StringUtil;

public class OrderDao extends BaseDao<Orders> {
	public Orders findByOrdersId(String orderId) {
		try {
			return (Orders) getEm()
					.createQuery("FROM Orders WHERE id = :orderId")
					.setParameter("orderId", orderId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	public PageStore<Orders> queryOrderPageAction(OrderListCriteria criteria) {

		PageStore<Orders> result = new PageStore<Orders>();

		result.setResultList(this.orderList(criteria));
		result.setResultCount(this.countOrder(criteria));

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Orders> orderList(OrderListCriteria criteria) {
		List<Orders> result = new ArrayList<Orders>();

		Query query = getFetchOrderQuery(SEARCH, criteria);

		result = query.getResultList();

		return result;
	}

	public int countOrder(OrderListCriteria criteria) {

		int count = 0;
		Query query = getFetchOrderQuery(COUNT, criteria);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by Order method, result count : {}", count);
		return count;
	}

	private Query getFetchOrderQuery(String type, OrderListCriteria vo) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT o FROM Orders o  where  o.deleted= :deleted");
			param.put("deleted", vo.isDeleted());
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(o) FROM Orders o where  o.deleted= :deleted");
			param.put("deleted", vo.isDeleted());
		}
		if (vo.getOrderStatus() != null) {
			eql.append(" AND o.orderStatus = :orderStatus ");
			param.put("orderStatus", vo.getOrderStatus());
		}
		boolean fal=false;
		if(!StringUtil.isEmptyString(vo.getPhone()))
		{
			eql.append(" AND o.orderPerson.phone = :phone ");
			param.put("phone", vo.getPhone());
			fal=true;
		}

		if(vo.getOrderStatus()!=null)
		{
			eql.append(" AND o.orderStatus = :orderStatus "); 
			param.put("orderStatus", vo.getOrderStatus());
			fal=true;
		}
		if(!StringUtil.isEmptyString(vo.getCorpId()))
		{
			eql.append(" AND o.corporation.id = :corpId ");
			param.put("corpId", vo.getCorpId());
			fal=true;
		}
		if(!StringUtil.isEmptyString(vo.getDeptId()))
		{
			eql.append(" AND o.department.id = :deptId ");
			param.put("deptId", vo.getDeptId());
			fal=true; 
		}
		if(!StringUtil.isEmptyString(vo.getPhoneorName()))
		{
			eql.append(" AND (UPPER(o.orderPerson.phone) LIKE :keywords "
					+ " OR UPPER(o.orderPerson.name) LIKE :keywords "
					+ " OR UPPER(o.contactPerson.name) LIKE :keywords "
					+ " OR UPPER(o.contactPerson.phone) LIKE :keywords "
					+") ");
			param.put("keywords", "%"
					+ vo.getPhoneorName().trim().toUpperCase() + "%");

			fal=true;
		}
		if(fal==false)
		{
			eql.append(" AND o.id = 'notData' ");	
		}
		
		if (SEARCH.equals(type)) {
			if (vo!=null && vo.getSortingDetail() != null && vo.getSortingDetail().getSort()!=null && vo.getSortingDetail().getDirection()!=null) {
				eql.append(" ORDER BY o." + vo.getSortingDetail().getSort()
						+ " " + vo.getSortingDetail().getDirection());
			}
			else
			{
				eql.append(" ORDER BY o.placeOrderTime DESC");
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
