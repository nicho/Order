package com.omdasoft.orderonline.gwt.order.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.dishes.DishesType;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model.DishesTypeListClient;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.SearchDishesTypeListRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.SearchDishesTypeListResponse;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.dishes.DishesTypeSearchCriteria;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.dishes.DishesService;

public class SearchDishesTypeListActionHandler extends
		BaseActionHandler<SearchDishesTypeListRequest, SearchDishesTypeListResponse> {

	DishesService dishesService;
	
	@Inject
	public SearchDishesTypeListActionHandler(DishesService dishesService)
	{
		this.dishesService=dishesService;
	}
	public SearchDishesTypeListActionHandler() {

	}
	@Override
	public Class<SearchDishesTypeListRequest> getActionType() {
		return SearchDishesTypeListRequest.class;
	}

	@Override
	public SearchDishesTypeListResponse execute(SearchDishesTypeListRequest action, ExecutionContext context)
			throws DispatchException {
		SearchDishesTypeListResponse rep=new SearchDishesTypeListResponse();
		UserContext u=new UserContext();
		if(action.getSession()!=null)
		{
			u.setCorporationId(action.getSession().getCorporationId());
			u.setUserId(action.getSession().getToken());
			u.setDeptId(action.getSession().getDepartmentId());
		}
		DishesTypeSearchCriteria criteria=new DishesTypeSearchCriteria();
		if (action.getCriteria().getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(action.getCriteria().getPagination().getLimit());
			detail.setStart(action.getCriteria().getPagination().getStart());

			criteria.setPaginationDetail(detail);
		}
		if(action.getCriteria()!=null &&  !StringUtil.isEmpty(action.getCriteria().getDeptId()))
			criteria.setDeptId(action.getCriteria().getDeptId());
		PageStore<DishesType> userpage= dishesService.getDishesTypeList(u, criteria);
		rep.setTotal(userpage.getResultCount());
		
		List<DishesTypeListClient> result=new ArrayList<DishesTypeListClient>();
		if(userpage.getResultList()!=null && userpage.getResultList().size()>=0)
		{
			for (DishesType d:userpage.getResultList()) {
				DishesTypeListClient r=new DishesTypeListClient();
				r.setId(d.getId());
				r.setName(d.getName());
				r.setRid(d.getRid());
				result.add(r);
			}
		}
		rep.setResult(result);
		return rep;
	}

	@Override
	public void rollback(SearchDishesTypeListRequest action, SearchDishesTypeListResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
