package com.omdasoft.orderonline.gwt.order.client.server.order;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.order.Orders;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListClient;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria.OrderStatus;
import com.omdasoft.orderonline.gwt.order.client.orderList.request.SearchOrderListRequest;
import com.omdasoft.orderonline.gwt.order.client.orderList.request.SearchOrderListResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.util.UserRoleTool;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.order.OrderListCriteria;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.order.OrderService;
import com.omdasoft.orderonline.util.StringUtil;

public class SearchOrderListActionHandler extends
		BaseActionHandler<SearchOrderListRequest, SearchOrderListResponse> {

	OrderService OrderService;
	
	@Inject
	public SearchOrderListActionHandler(OrderService OrderService)
	{
		this.OrderService=OrderService;
	}
	public SearchOrderListActionHandler() {

	}
	@Override
	public Class<SearchOrderListRequest> getActionType() {
		return SearchOrderListRequest.class;
	}

	@Override
	public SearchOrderListResponse execute(SearchOrderListRequest action, ExecutionContext context)
			throws DispatchException {
		SearchOrderListResponse rep=new SearchOrderListResponse();
		UserContext u=new UserContext();
		u.setCorporationId(action.getSession().getCorporationId());
		u.setUserId(action.getSession().getToken());
		u.setUserRoles(UserRoleTool.adaptToRole(action.getSession().getUserRoles()));
		u.setDeptId(action.getSession().getDepartmentId());
		OrderListCriteria criteria=new OrderListCriteria();
		if (action.getCriteria().getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(action.getCriteria().getPagination().getLimit());
			detail.setStart(action.getCriteria().getPagination().getStart());

			criteria.setPaginationDetail(detail);
		}
		if(!StringUtil.isEmptyString(action.getCriteria().getPhone()))
		{
			criteria.setPhone(action.getCriteria().getPhone());
		}
		if(!StringUtil.isEmptyString(action.getCriteria().getPhoneorName()))
		{
			criteria.setPhoneorName(action.getCriteria().getPhoneorName());
		}
		if(action.getCriteria().getStatus()!=null)
		{
			criteria.setOrderStatus(com.omdasoft.orderonline.model.order.OrderStatus.valueOf(action.getCriteria().getStatus().toString()));
		}
		PageStore<Orders> Orderpage= OrderService.getOrderList(u, criteria);
		rep.setTotal(Orderpage.getResultCount());
		
		List<OrderListClient> result=new ArrayList<OrderListClient>();
		if(Orderpage.getResultList()!=null && Orderpage.getResultList().size()>=0)
		{
			for (Orders order:Orderpage.getResultList()) {
				OrderListClient r=new OrderListClient();
				r.setId(order.getId());
				r.setCode(order.getCode());
				r.setAmountOfClient(order.getAmountOfClient());
				r.setCity(order.getCity());
				r.setFavoriteRoom(order.getFavoriteRoom());
				r.setMemo(order.getMemo());
				r.setPlaceOrderTime(order.getPlaceOrderTime());
				r.setReserveTimeDate(order.getReserveTimeDate());
				r.setReserveTimeDateH(order.getReserveTimeDateH());
				r.setReserveTimeDateS(order.getReserveTimeDateS());
				r.setProcessingTime(order.getHandleTime());
				r.setCompleteTime(order.getFinishTime());
				r.setLastUpdateTime(order.getModifyTime());
				r.setResult(order.getResult());
				if(order.getOrderStatus()!=null)
				r.setOrderStatus(OrderStatus.valueOf(order.getOrderStatus().toString()));
				result.add(r);
			}
		}
		rep.setResult(result);
		return rep;
	}

	@Override
	public void rollback(SearchOrderListRequest action, SearchOrderListResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
