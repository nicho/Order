package com.omdasoft.orderonline.gwt.order.client.company.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.omdasoft.orderonline.gwt.order.client.company.model.CompanyListClient;
import com.omdasoft.orderonline.gwt.order.client.company.model.CompanySearchVo;
import com.omdasoft.orderonline.gwt.order.client.company.presenter.CompanyListPresenter.CompanyListDisplay;
import com.omdasoft.orderonline.gwt.order.client.company.request.CompanyListRequest;
import com.omdasoft.orderonline.gwt.order.client.company.request.CompanyListResponse;
import com.omdasoft.orderonline.gwt.order.client.dataprovider.BaseDataProvider;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;

public class CompanyListViewAdapter extends BaseDataProvider<CompanyListClient>{
	final DispatchAsync dispatch;
	final CompanySearchVo criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final CompanyListDisplay display;
	
	public CompanyListViewAdapter(DispatchAsync dispatch, CompanySearchVo criteria,
			ErrorHandler errorHandler, SessionManager sessionManager, CompanyListDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}
	
	public void fetchData(final int start, final int length) {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		criteria.setPagination(pagination);
		if (getSorting() != null) {
			criteria.setSorting(getSorting());
		}
		dispatch.execute(new CompanyListRequest(criteria, sessionManager.getSession()),
				new AsyncCallback<CompanyListResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(CompanyListResponse response) {
						updateRowData(start, response.getResult());
						updateRowCount(response.getTotal(), true);
					}

				});
	}
}
