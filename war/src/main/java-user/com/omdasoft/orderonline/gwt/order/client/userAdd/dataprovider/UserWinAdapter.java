package com.omdasoft.orderonline.gwt.order.client.userAdd.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.omdasoft.orderonline.gwt.order.client.dataprovider.BaseDataProvider;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.userView.model.UserWinClient;
import com.omdasoft.orderonline.gwt.order.client.userView.model.UserWinCriteria;
import com.omdasoft.orderonline.gwt.order.client.userView.presenter.UserViewPresenter.UserViewDisplay;
import com.omdasoft.orderonline.gwt.order.client.userView.request.UserWinRequest;
import com.omdasoft.orderonline.gwt.order.client.userView.request.UserWinResponse;
import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;

public class UserWinAdapter extends BaseDataProvider<UserWinClient> {

	final DispatchAsync dispatch;
	final UserViewDisplay display;
	UserWinCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public UserWinAdapter(DispatchAsync dispatch,
			UserWinCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, UserViewDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<StaffWinClient> list = new ArrayList<StaffWinClient>();
		// for (int i = start; i < start + length; i++) {
		// StaffWinClient item = new StaffWinClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(StaffListStatus.TO_BE_ISSUE);
		// list.add(item);
		// }
		//
		// updateRowData(start, list);
		// updateRowCount(100, true);
		// } else {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		getCriteria().setPagination(pagination);
		if (getSorting() != null) {
			getCriteria().setSorting(getSorting());
		}
		dispatch.execute(new UserWinRequest(getCriteria()), new AsyncCallback<UserWinResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(UserWinResponse response) {
				updateRowData(start, response.getResult());
				updateRowCount(response.getTotal(), true);
			
			}

		});
		// }
	}


	public void setCriteria(UserWinCriteria criteria) {
		this.criteria = criteria;
	}

	private UserWinCriteria getCriteria() {
		if (criteria == null) {
			criteria = new UserWinCriteria();
		}

		return criteria;
	}
}
