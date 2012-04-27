/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userView.model;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;
import com.omdasoft.orderonline.gwt.order.model.SortingDetailClient;

/**
 * @author nicho
 * @since 2012年2月14日 11:29:59
 */
public class UserWinCriteria implements IsSerializable {

	
	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;
	private String staffId;



	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public PaginationDetailClient getPagination() {
		return pagination;
	}

	public void setPagination(PaginationDetailClient pagination) {
		this.pagination = pagination;
	}

	public SortingDetailClient getSorting() {
		return sorting;
	}

	public void setSorting(SortingDetailClient sorting) {
		this.sorting = sorting;
	}




}
