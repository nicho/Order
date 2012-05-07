package com.omdasoft.orderonline.model.dishes;


import java.io.Serializable;

import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.common.SortingDetail;

/**
 * This class is designed to wrap the parameter to search main-accounts using
 * paging.
 * 
 * @author yanxin
 * @since 0.0.1 2010-9-26
 */
public class DishesTypeSearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8038695352233973821L;


	private String corporationId;
	private String deptId;
	private String keyName;
	
	
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	private PaginationDetail paginationDetail = new PaginationDetail();

	private SortingDetail sortingDetail = new SortingDetail();

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
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
