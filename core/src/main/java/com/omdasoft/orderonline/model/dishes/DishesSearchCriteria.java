package com.omdasoft.orderonline.model.dishes;

import java.io.Serializable;

import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.common.SortingDetail;

public class DishesSearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8038695352233973821L;

	String dishesTypeId;
	String deptId;
	String corporationId;
	private String keyName;
	
	
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	private PaginationDetail paginationDetail = new PaginationDetail();

	private SortingDetail sortingDetail = new SortingDetail();

	public DishesSearchCriteria() {
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDishesTypeId() {
		return dishesTypeId;
	}

	public void setDishesTypeId(String dishesTypeId) {
		this.dishesTypeId = dishesTypeId;
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
