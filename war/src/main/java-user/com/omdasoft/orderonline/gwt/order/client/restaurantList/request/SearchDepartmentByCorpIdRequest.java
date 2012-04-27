package com.omdasoft.orderonline.gwt.order.client.restaurantList.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class SearchDepartmentByCorpIdRequest implements
		Action<SearchDepartmentByCorpIdResponse> {

	private String corporationId;

	public SearchDepartmentByCorpIdRequest() {
	}

	public SearchDepartmentByCorpIdRequest(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

}
