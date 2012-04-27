package com.omdasoft.orderonline.gwt.order.client.restaurantList.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.omdasoft.orderonline.gwt.order.client.dataprovider.BaseDataProvider;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.LeaderSearchCriteria;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.StaffClient;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.SearchLeaderRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.SearchLeaderResponse;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;

public class SearchLeaderAsyncDataProvider extends
		BaseDataProvider<StaffClient> {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final LeaderSearchCriteria criteria;
	final boolean filterByAcl;

	public SearchLeaderAsyncDataProvider(DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			LeaderSearchCriteria criteria, boolean filterByAcl) {
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.criteria = criteria;
		this.filterByAcl = filterByAcl;
	}

	@Override
	public void fetchData(final int start, final int length) {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		criteria.setPagination(pagination);
		criteria.setSorting(getSorting());
		dispatch.execute(
				new SearchLeaderRequest(criteria, sessionManager
						.getSession(), filterByAcl),
				new AsyncCallback<SearchLeaderResponse>() {

					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchLeaderResponse response) {
						updateRowData(start, response.getResult().getResult());
						updateRowCount(response.getResult().getTotal(), true);
					}
				});
	}

}
