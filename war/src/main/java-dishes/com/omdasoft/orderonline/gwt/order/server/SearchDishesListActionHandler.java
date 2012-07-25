package com.omdasoft.orderonline.gwt.order.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.dishes.Dishes;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.DishesListClient;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.SearchDishesListRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.SearchDishesListResponse;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.common.SortingDetail;
import com.omdasoft.orderonline.model.dishes.DishesSearchCriteria;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.service.dishes.DishesService;

public class SearchDishesListActionHandler extends
		BaseActionHandler<SearchDishesListRequest, SearchDishesListResponse> {

	DishesService dishesService;
	
	@Inject
	public SearchDishesListActionHandler(DishesService dishesService)
	{
		this.dishesService=dishesService;
	}
	public SearchDishesListActionHandler() {

	}
	@Override
	public Class<SearchDishesListRequest> getActionType() {
		return SearchDishesListRequest.class;
	}

	@Override
	public SearchDishesListResponse execute(SearchDishesListRequest action, ExecutionContext context)
			throws DispatchException {
		SearchDishesListResponse rep=new SearchDishesListResponse();
		UserContext u=new UserContext();
		if(action.getSession()!=null)
		{
			u.setCorporationId(action.getSession().getCorporationId());
			u.setUserId(action.getSession().getToken());
			u.setDeptId(action.getSession().getDepartmentId());
			u.setLastRole(UserRole.valueOf(action.getSession().getLastLoginRole().toString()));
		}
		DishesSearchCriteria criteria=new DishesSearchCriteria();
		if (action.getCriteria().getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(action.getCriteria().getPagination().getLimit());
			detail.setStart(action.getCriteria().getPagination().getStart());

			criteria.setPaginationDetail(detail);
		}
		
		if (action.getCriteria().getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(action.getCriteria().getSorting().getSort());
			sortingDetail.setDirection(action.getCriteria().getSorting().getDirection());
			criteria.setSortingDetail(sortingDetail);
		}
		if(!StringUtil.isEmpty(action.getCriteria().getTypeId()))
			criteria.setDishesTypeId(action.getCriteria().getTypeId());
		if(action.getCriteria()!=null &&  !StringUtil.isEmpty(action.getCriteria().getDeptId()))
			criteria.setDeptId(action.getCriteria().getDeptId());
		if(!StringUtil.isEmpty(action.getCriteria().getKeyName()))
			criteria.setKeyName(action.getCriteria().getKeyName());
		
		if(!StringUtil.isEmpty(action.getCriteria().getCorpId()))
		criteria.setCorporationId(action.getCriteria().getCorpId());
		
		PageStore<Dishes> userpage= dishesService.getDishesList(u, criteria);
		rep.setTotal(userpage.getResultCount());

		List<DishesListClient> result=new ArrayList<DishesListClient>();
		if(userpage.getResultList()!=null && userpage.getResultList().size()>=0)
		{
			int indexNo=1;
			if(action.getCriteria()!=null && action.getCriteria().getPagination()!=null && action.getCriteria().getPagination().getStart()!=0)
				indexNo=action.getCriteria().getPagination().getStart()+1;
			for (Dishes d:userpage.getResultList()) {
				DishesListClient r=new DishesListClient();
				r.setIndexNo(indexNo+"");
				r.setId(d.getId());
				r.setName(d.getName());
				r.setDescription(d.getDescription());
				if(d.getDishesType()!=null)
				r.setDishesType(d.getDishesType().getName());
				r.setPhoto(d.getPhoto());
				r.setPrice(d.getPrice()+"");
				r.setRid(d.getRid());
				r.setTaste(d.getTaste());
				result.add(r);
				indexNo++;
			}
		}
		rep.setResult(result);
		return rep;
	}

	@Override
	public void rollback(SearchDishesListRequest action, SearchDishesListResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
