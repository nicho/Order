package com.omdasoft.orderonline.gwt.order.client.company.request;

import net.customware.gwt.dispatch.shared.Action;

public class CompanyInitRequest implements Action<CompanyInitResponse>{
	private String companyId;
	
	public CompanyInitRequest() {
	}

	public CompanyInitRequest(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
