package com.omdasoft.orderonline.gwt.order.client.orderSave.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class CityInitResponse implements Result {

	private List<String[]> deptName;

	public CityInitResponse() {

	}

	public CityInitResponse(List<String[]> deptName) {
		this.deptName = deptName;

	}

	public List<String[]> getDeptName() {
		return deptName;
	}

	public void setDeptName(List<String[]> deptName) {
		this.deptName = deptName;
	}


}
