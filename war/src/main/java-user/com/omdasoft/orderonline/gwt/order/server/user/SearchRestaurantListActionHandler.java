package com.omdasoft.orderonline.gwt.order.server.user;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.model.RestaurantListClient;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.request.SearchRestaurantListRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.request.SearchRestaurantListResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.common.SortingDetail;
import com.omdasoft.orderonline.model.org.search.DepartmentListVo;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.org.DepartmentService;

public class SearchRestaurantListActionHandler extends
		BaseActionHandler<SearchRestaurantListRequest, SearchRestaurantListResponse> {

	DepartmentService departmentService;
	@Inject
	public SearchRestaurantListActionHandler(DepartmentService departmentService)
	{
		this.departmentService=departmentService;
	}
	public SearchRestaurantListActionHandler() {

	}
	@Override
	public Class<SearchRestaurantListRequest> getActionType() {
		return SearchRestaurantListRequest.class;
	}

	@Override
	public SearchRestaurantListResponse execute(SearchRestaurantListRequest request, ExecutionContext context)
			throws DispatchException {
		SearchRestaurantListResponse rep=new SearchRestaurantListResponse();
		UserContext u=new UserContext();
		u.setCorporationId(request.getSession().getCorporationId());
		u.setUserId(request.getSession().getToken());
		u.setDeptId(request.getSession().getDepartmentId());
		
		DepartmentListVo criteria=new DepartmentListVo();
		
		if (request.getCriteria().getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(request.getCriteria().getPagination().getLimit());
			detail.setStart(request.getCriteria().getPagination().getStart());

			criteria.setPaginationDetail(detail);
		}

		if (request.getCriteria().getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(request.getCriteria().getSorting().getSort());
			sortingDetail.setDirection(request.getCriteria().getSorting().getDirection());
			criteria.setSortingDetail(sortingDetail);
		}

		PageStore<Department> userpage=departmentService.departmentList(u, criteria);
		
		
		rep.setTotal(userpage.getResultCount());
		
		List<RestaurantListClient> result=new ArrayList<RestaurantListClient>();
		if(userpage.getResultList()!=null && userpage.getResultList().size()>=0)
		{
			int indexNo=1;
			if(request.getCriteria()!=null && request.getCriteria().getPagination()!=null && request.getCriteria().getPagination().getStart()!=0)
				indexNo=request.getCriteria().getPagination().getStart()+1;
			for (Department r:userpage.getResultList()) {
				RestaurantListClient c=new RestaurantListClient();
				c.setIndexNo(indexNo+"");
				c.setId(r.getId());
				c.setName(r.getName());
				c.setCity(r.getCity());
				c.setAddress(r.getAddress());
				c.setPhone(r.getPhone());
				c.setDid(r.getDid());
				if(r.getDeptAdmin()!=null && r.getDeptAdmin().getStaff()!=null)
				c.setDeptAdmin(r.getDeptAdmin().getStaff().getName());
				result.add(c);
				indexNo++;
			}
		}
		rep.setResult(result);
		return rep;
	}

	@Override
	public void rollback(SearchRestaurantListRequest action, SearchRestaurantListResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
