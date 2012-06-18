/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesList.model;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;
import com.omdasoft.orderonline.gwt.order.model.SortingDetailClient;

/**
 * @author nicho
 * @since 2012年2月14日 11:29:59
 */
public class DishesListCriteria implements IsSerializable {

	
	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;
	private String typeId;
	private String deptId;
	private String keyName;
	private String corpId;
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}


}
