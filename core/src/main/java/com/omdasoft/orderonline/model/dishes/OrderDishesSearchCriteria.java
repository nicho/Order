package com.omdasoft.orderonline.model.dishes;

import java.io.Serializable;

import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.common.SortingDetail;

public class OrderDishesSearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8038695352233973821L;

	String orderId;

	private PaginationDetail paginationDetail = new PaginationDetail();

	private SortingDetail sortingDetail = new SortingDetail();

	public OrderDishesSearchCriteria() {
	}

	

	public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public PaginationDetail getPaginationDetail() {
		return paginationDetail;
	}

	public void setPaginationDetail(PaginationDetail paginationDetail) {
		this.paginationDetail = paginationDetail;
	}

	public SortingDetail getSortingDetail() {
		return sortingDetail;
	}

	public void setSortingDetail(SortingDetail sortingDetail) {
		this.sortingDetail = sortingDetail;
	}

}
