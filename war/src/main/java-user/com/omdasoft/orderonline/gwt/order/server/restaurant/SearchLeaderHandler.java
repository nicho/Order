package com.omdasoft.orderonline.gwt.order.server.restaurant;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.google.inject.Inject;
import com.omdasoft.orderonline.dao.org.StaffDao.QueryStaffPageActionResult;
import com.omdasoft.orderonline.domain.org.Staff;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.SearchLeaderResult;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.StaffClient;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.SearchLeaderRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.SearchLeaderResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.server.logger.InjectLogger;
import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.common.SortingDetail;
import com.omdasoft.orderonline.model.staff.StaffSearchCriteria;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.staff.IStaffService;

public class SearchLeaderHandler extends	BaseActionHandler<SearchLeaderRequest, SearchLeaderResponse> {

	@InjectLogger
	Logger log;
	IStaffService staffService;

	@Inject
	public SearchLeaderHandler(IStaffService staffService)
	{
		this.staffService=staffService;
	}
	@Override
	public SearchLeaderResponse execute(SearchLeaderRequest request,
			ExecutionContext arg1) throws DispatchException {

		String corporationId = request.getUserSession().getCorporationId() ;//企业ID  
		StaffSearchCriteria  criteria=new StaffSearchCriteria();
		if (request.getCriteria().getPagination() != null) {
			PaginationDetail paginationDetail = new PaginationDetail();
			paginationDetail.setStart(request.getCriteria().getPagination()
					.getStart());
			paginationDetail.setLimit(request.getCriteria().getPagination()
					.getLimit());
			criteria.setPaginationDetail(paginationDetail);
		}

		if (request.getCriteria().getSorting() != null) {
			SortingDetail sorting = new SortingDetail();
			sorting.setSort(request.getCriteria().getSorting().getSort());
			sorting.setDirection(request.getCriteria().getSorting()
					.getDirection());
			criteria.setSortingDetail(sorting);
		}

		UserContext context =new UserContext();
		context.setCorporationId(corporationId);
		
		QueryStaffPageActionResult store = staffService.queryStaffList(criteria, context);
		
		SearchLeaderResult result = new SearchLeaderResult();


		result.setResult(adapter(store.getResultList()));
		result.setTotal(store.getTotal());

		return new SearchLeaderResponse(result);
	}


	private List<StaffClient> adapter(List<Staff> records) {
		
		List<StaffClient> list = new ArrayList<StaffClient>();
		System.out.println("dd="+records);
		for (Staff record : records) {
			StaffClient staff = new StaffClient();
			staff.setId(record.getId());
			staff.setName(record.getName());

			
			list.add(staff);
		}

		return list;
	}
	

	@Override
	public Class<SearchLeaderRequest> getActionType() {
		return SearchLeaderRequest.class;
	}

	@Override
	public void rollback(SearchLeaderRequest arg0, SearchLeaderResponse arg1,
			ExecutionContext arg2) throws DispatchException {

	}

}
