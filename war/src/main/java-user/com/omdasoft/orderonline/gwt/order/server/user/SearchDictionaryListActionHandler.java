package com.omdasoft.orderonline.gwt.order.server.user;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.dictionary.Dictionary;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.model.DictionaryListClient;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.request.SearchDictionaryListRequest;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.request.SearchDictionaryListResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.user.DictionarySearchCriteria;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.user.UserService;

public class SearchDictionaryListActionHandler extends
		BaseActionHandler<SearchDictionaryListRequest, SearchDictionaryListResponse> {

	UserService userService;
	
	@Inject
	public SearchDictionaryListActionHandler(UserService userService)
	{
		this.userService=userService;
	}
	public SearchDictionaryListActionHandler() {

	}
	@Override
	public Class<SearchDictionaryListRequest> getActionType() {
		return SearchDictionaryListRequest.class;
	}

	@Override
	public SearchDictionaryListResponse execute(SearchDictionaryListRequest action, ExecutionContext context)
			throws DispatchException {
		SearchDictionaryListResponse rep=new SearchDictionaryListResponse();
		UserContext u=new UserContext();
		if(action.getSession()!=null)
		{
			u.setCorporationId(action.getSession().getCorporationId());
			u.setUserId(action.getSession().getToken());
			u.setDeptId(action.getSession().getDepartmentId());
		}
		
		DictionarySearchCriteria criteria=new DictionarySearchCriteria();
		if (action.getCriteria().getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(action.getCriteria().getPagination().getLimit());
			detail.setStart(action.getCriteria().getPagination().getStart());

			criteria.setPaginationDetail(detail);
		}
		criteria.setDictionaryType(action.getCriteria().getDictionaryType());
		PageStore<Dictionary> userpage= userService.getDictionaryListList(u, criteria);
		rep.setTotal(userpage.getResultCount());
		
		List<DictionaryListClient> result=new ArrayList<DictionaryListClient>();
		if(userpage.getResultList()!=null && userpage.getResultList().size()>=0)
		{
			for (Dictionary d:userpage.getResultList()) {
				DictionaryListClient r=new DictionaryListClient();
				r.setId(d.getId());
				r.setName(d.getName());
				r.setDictionaryType(d.getDictionaryType());
				result.add(r);
			}
		}
		rep.setResult(result);
		return rep;
	}

	@Override
	public void rollback(SearchDictionaryListRequest action, SearchDictionaryListResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
