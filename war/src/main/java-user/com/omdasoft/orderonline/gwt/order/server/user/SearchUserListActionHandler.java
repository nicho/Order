package com.omdasoft.orderonline.gwt.order.server.user;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.gwt.order.client.userList.model.UserListClient;
import com.omdasoft.orderonline.gwt.order.client.userList.request.SearchUserListRequest;
import com.omdasoft.orderonline.gwt.order.client.userList.request.SearchUserListResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.util.UserRoleTool;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.common.SortingDetail;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserSearchCriteria;
import com.omdasoft.orderonline.service.user.UserService;

public class SearchUserListActionHandler extends
		BaseActionHandler<SearchUserListRequest, SearchUserListResponse> {

	UserService userService;
	
	@Inject
	public SearchUserListActionHandler(UserService userService)
	{
		this.userService=userService;
	}
	public SearchUserListActionHandler() {

	}
	@Override
	public Class<SearchUserListRequest> getActionType() {
		return SearchUserListRequest.class;
	}

	@Override
	public SearchUserListResponse execute(SearchUserListRequest action, ExecutionContext context)
			throws DispatchException {
		SearchUserListResponse rep=new SearchUserListResponse();
		UserContext u=new UserContext();	
		u.setCorporationId(action.getSession().getCorporationId());
		u.setUserId(action.getSession().getToken());
		u.setLoginName(action.getSession().getLoginName());
		u.setUserRoles(UserRoleTool.adaptToRole(action.getSession().getUserRoles()));
	
		UserSearchCriteria criteria=new UserSearchCriteria();
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
		PageStore<SysUser> userpage= userService.getUserList(u, criteria);
		rep.setTotal(userpage.getResultCount());
		
		List<UserListClient> result=new ArrayList<UserListClient>();
		if(userpage.getResultList()!=null && userpage.getResultList().size()>=0)
		{
			int indexNo=1;
			if(action.getCriteria()!=null && action.getCriteria().getPagination()!=null && action.getCriteria().getPagination().getStart()!=0)
				indexNo=action.getCriteria().getPagination().getStart()+1;
			for (SysUser user:userpage.getResultList()) {
				UserListClient r=new UserListClient();
				r.setIndexNo(indexNo+"");
				r.setStaffId(user.getStaff().getId());
				r.setEmail(user.getStaff().getEmail());
				r.setStaffName(user.getStaff().getName());
				r.setPhone(user.getStaff().getPhone());
				r.setDepartmentName(user.getStaff().getDepartment().getName());
				r.setStatus(user.getStatus().getDisplayName());
				result.add(r);
				indexNo++;
			}
		}
		rep.setResult(result);
		return rep;
	}

	@Override
	public void rollback(SearchUserListRequest action, SearchUserListResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
