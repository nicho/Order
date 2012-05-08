package com.omdasoft.orderonline.gwt.order.client.server.order;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.order.OrdersDishes;
import com.omdasoft.orderonline.gwt.order.client.orderView.model.OrderDishesListClient;
import com.omdasoft.orderonline.gwt.order.client.orderView.request.SearchOrderDishesListRequest;
import com.omdasoft.orderonline.gwt.order.client.orderView.request.SearchOrderDishesListResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.common.SortingDetail;
import com.omdasoft.orderonline.model.dishes.OrderDishesSearchCriteria;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.dishes.DishesService;
import com.omdasoft.orderonline.util.StringUtil;

public class SearchOrderDishesListActionHandler extends
		BaseActionHandler<SearchOrderDishesListRequest, SearchOrderDishesListResponse> {

	DishesService DishesService;
	
	@Inject
	public SearchOrderDishesListActionHandler(DishesService DishesService)
	{
		this.DishesService=DishesService;
	}
	public SearchOrderDishesListActionHandler() {

	}
	@Override
	public Class<SearchOrderDishesListRequest> getActionType() {
		return SearchOrderDishesListRequest.class;
	}

	@Override
	public SearchOrderDishesListResponse execute(SearchOrderDishesListRequest action, ExecutionContext context)
			throws DispatchException {
		SearchOrderDishesListResponse rep=new SearchOrderDishesListResponse();
		UserContext u=new UserContext();
		OrderDishesSearchCriteria criteria=new OrderDishesSearchCriteria();
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
		if(!StringUtil.isEmptyString(action.getCriteria().getOrderId()))
		{
			criteria.setOrderId(action.getCriteria().getOrderId());
		}
		PageStore<OrdersDishes> dishespage= DishesService.getOrderDishesList(u, criteria);
		rep.setTotal(dishespage.getResultCount());
		
		List<OrderDishesListClient> result=new ArrayList<OrderDishesListClient>();
		if(dishespage.getResultList()!=null && dishespage.getResultList().size()>=0)
		{
			for (OrdersDishes dishe:dishespage.getResultList()) {
				OrderDishesListClient r=new OrderDishesListClient();
				r.setId(dishe.getId());
				
				if(dishe.getDishes()!=null)
				{
					if(dishe.getDishes().getDishesType()!=null)
						r.setDishesType(dishe.getDishes().getDishesType().getName());
					r.setDishesName(dishe.getDishes().getName());
					r.setPhoto(dishe.getDishes().getPhoto());
					r.setPrice(dishe.getDishes().getPrice()+"");
				}
				r.setNumber(dishe.getNumber()+"");
				r.setTotalprice(dishe.getPrice());
				r.setUnit(dishe.getUnit());
				r.setTaste(dishe.getTaste());
				result.add(r);
			}
		}
		rep.setResult(result);
		return rep;
	}

	@Override
	public void rollback(SearchOrderDishesListRequest action, SearchOrderDishesListResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
