package com.omdasoft.orderonline.gwt.order.client.orderView.dateprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.omdasoft.orderonline.gwt.order.client.dataprovider.BaseDataProvider;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.orderView.model.OrderDishesCriteria;
import com.omdasoft.orderonline.gwt.order.client.orderView.model.OrderDishesListClient;
import com.omdasoft.orderonline.gwt.order.client.orderView.presenter.OrderViewPresenter.OrderViewDisplay;
import com.omdasoft.orderonline.gwt.order.client.orderView.request.SearchOrderDishesListRequest;
import com.omdasoft.orderonline.gwt.order.client.orderView.request.SearchOrderDishesListResponse;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;

public class OrderDishesListAdapter extends BaseDataProvider<OrderDishesListClient> {

	final DispatchAsync dispatch;
	final OrderViewDisplay display;
	OrderDishesCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public OrderDishesListAdapter(DispatchAsync dispatch,
			OrderDishesCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, OrderViewDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<OrderDishesListClient> list = new ArrayList<OrderDishesListClient>();
		// for (int i = start; i < start + length; i++) {
		// OrderDishesListClient item = new OrderDishesListClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(OrderListStatus.TO_BE_ISSUE);
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
		dispatch.execute(new SearchOrderDishesListRequest(getCriteria(), sessionManager
				.getSession()), new AsyncCallback<SearchOrderDishesListResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(SearchOrderDishesListResponse response) {
				updateRowData(start, response.getResult());
				updateRowCount(response.getTotal(), true);
				display.setDataCount(response.getTotal()+"");
			}

		});
		// }
	}


	public void setCriteria(OrderDishesCriteria criteria) {
		this.criteria = criteria;
	}

	private OrderDishesCriteria getCriteria() {
		if (criteria == null) {
			criteria = new OrderDishesCriteria();
		}

		return criteria;
	}
}
