package com.omdasoft.orderonline.model.order;

import java.io.Serializable;
import java.util.Date;

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
	private CarteState carteState;
	private boolean deleted;
	private String phone;
	private String phoneorName;
	private String corpId;
	private String deptId;

	private String dateType;
	private Date dateStart;
	private Date dateEnd;
	private boolean notDept;
	


	public CarteState getCarteState() {
		return carteState;
	}

	public void setCarteState(CarteState carteState) {
		this.carteState = carteState;
	}

	public boolean isNotDept() {
		return notDept;
	}

	public void setNotDept(boolean notDept) {
		this.notDept = notDept;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
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
