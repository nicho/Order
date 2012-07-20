package com.omdasoft.orderonline.gwt.order.client.company.model;

import java.io.Serializable;

import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;
import com.omdasoft.orderonline.gwt.order.model.SortingDetailClient;

public class CompanySearchVo implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanySearchVo() {
	}
	
	private PaginationDetailClient pagination;
	private SortingDetailClient sorting;
	private String name;       	  	//企业名称
//	private String corporation;   	//企业法人
//	private String address;       	//企业地址
//	private String linkman;       	//联系人
//	private String mobilePhone;   	//联系电话
//	private String telphone;      	//手机号
//	private String emailAddress;  	//email
//	private String web;       		//网站
//	private String fax;       		//传真
//	private String remark;      	//备注
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
