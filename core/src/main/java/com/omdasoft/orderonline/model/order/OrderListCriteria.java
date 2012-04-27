package com.omdasoft.orderonline.model.order;

import java.io.Serializable;

import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.common.SortingDetail;

public class OrderListCriteria implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -8038695352233973821L;
	private PaginationDetail paginationDetail = new PaginationDetail();

	private SortingDetail sortingDetail = new SortingDetail();

	private OrderStatus orderStatus;
	private boolean deleted;
	private String phone;
	private String phoneorName;
	private String corpId;
	private String deptId;

	
	
	public String getPhoneorName() {
		return phoneorName;
	}

	public void setPhoneorName(String phoneorName) {
		this.phoneorName = phoneorName;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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
