package com.omdasoft.orderonline.model.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.common.SortingDetail;

public class CompanySearchVo {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private PaginationDetail paginationDetail;

	private SortingDetail sortingDetail;
	// 公司名称
	private String name;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
