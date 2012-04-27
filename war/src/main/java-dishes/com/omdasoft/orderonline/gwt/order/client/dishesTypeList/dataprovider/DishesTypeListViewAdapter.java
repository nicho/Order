package com.omdasoft.orderonline.gwt.order.client.dishesTypeList.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.omdasoft.orderonline.gwt.order.client.dataprovider.BaseDataProvider;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model.DishesTypeListClient;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model.DishesTypeListCriteria;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.presenter.DishesTypeListPresenter.DishesTypeListDisplay;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.SearchDishesTypeListRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.SearchDishesTypeListResponse;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;

public class DishesTypeListViewAdapter extends BaseDataProvider<DishesTypeListClient> {

	final DispatchAsync dispatch;
	final DishesTypeListDisplay display;
	DishesTypeListCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public DishesTypeListViewAdapter(DispatchAsync dispatch,
			DishesTypeListCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, DishesTypeListDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<DishesTypeListClient> list = new ArrayList<DishesTypeListClient>();
		// for (int i = start; i < start + length; i++) {
		// DishesTypeListClient item = new DishesTypeListClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(DishesTypeListStatus.TO_BE_ISSUE);
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
		dispatch.execute(new SearchDishesTypeListRequest(getCriteria(), sessionManager
				.getSession()), new AsyncCallback<SearchDishesTypeListResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(SearchDishesTypeListResponse response) {
				updateRowData(start, response.getResult());
				updateRowCount(response.getTotal(), true);
				display.setDataCount(response.getTotal()+"");
			}

		});
		// }
	}


	public void setCriteria(DishesTypeListCriteria criteria) {
		this.criteria = criteria;
	}

	private DishesTypeListCriteria getCriteria() {
		if (criteria == null) {
			criteria = new DishesTypeListCriteria();
		}

		return criteria;
	}
}
