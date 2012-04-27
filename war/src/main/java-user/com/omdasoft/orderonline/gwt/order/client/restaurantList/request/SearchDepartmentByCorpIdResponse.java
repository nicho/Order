package com.omdasoft.orderonline.gwt.order.client.restaurantList.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.omdasoft.orderonline.gwt.order.client.restaurantList.model.DepartmentVo;

/**
 * @author yanrui
 * @since
 */
public class SearchDepartmentByCorpIdResponse implements Result {

	private List<DepartmentVo> departmentList;

	public SearchDepartmentByCorpIdResponse() {

	}

	public SearchDepartmentByCorpIdResponse(List<DepartmentVo> departmentList) {
		this.departmentList = departmentList;
	}

	public List<DepartmentVo> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DepartmentVo> departmentList) {
		this.departmentList = departmentList;
	}

}
