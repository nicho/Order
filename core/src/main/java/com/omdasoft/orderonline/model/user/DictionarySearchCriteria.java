package com.omdasoft.orderonline.model.user;


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
public class DictionarySearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8038695352233973821L;


	private int dictionaryType;
	private PaginationDetail paginationDetail = new PaginationDetail();

	private SortingDetail sortingDetail = new SortingDetail();

	private String corpId;
	private String deptId;

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

	public int getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(int dictionaryType) {
		this.dictionaryType = dictionaryType;
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
