package com.omdasoft.orderonline.dao.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.dictionary.Dictionary;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.user.DictionarySearchCriteria;
import com.omdasoft.orderonline.util.StringUtil;

public class DictionaryDao extends BaseDao<Dictionary> {

	public Dictionary findDictionaryById(String id) {
		Dictionary dishes = (Dictionary) getEm().createQuery("FROM Dictionary u WHERE u.id = :id").setParameter("id", id).getSingleResult();
		return dishes;
	}
	public PageStore<Dictionary> queryDictionaryPageAction(DictionarySearchCriteria criteria) {

		PageStore<Dictionary> result = new PageStore<Dictionary>();

		result.setResultList(this.dishesList(criteria));
		result.setResultCount(this.countuser(criteria));

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Dictionary> dishesList(DictionarySearchCriteria criteria) {
		List<Dictionary> result = new ArrayList<Dictionary>();

		Query query = getFetchDictionaryQuery(SEARCH, criteria);

		result = query.getResultList();

		return result;
	}

	public int countuser(DictionarySearchCriteria criteria) {

		int count = 0;
		Query query = getFetchDictionaryQuery(COUNT, criteria);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by user method, result count : {}", count);
		return count;
	}

	private Query getFetchDictionaryQuery(String type, DictionarySearchCriteria vo) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT o FROM Dictionary o  where  1=1 AND (o.deleted=0 or o.deleted is null) ");
		
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(o) FROM Dictionary o where  1=1 AND (o.deleted=0 or o.deleted is null) ");

		}
		if(vo.getDictionaryType()!=0)
		{
			eql.append(" AND o.dictionaryType = :dictionaryType ");
			param.put("dictionaryType", vo.getDictionaryType());
		}
		if(!StringUtil.isEmptyString(vo.getCorpId()))
		{
			eql.append(" AND o.corporation.id = :corpId ");
			param.put("corpId", vo.getCorpId());
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
