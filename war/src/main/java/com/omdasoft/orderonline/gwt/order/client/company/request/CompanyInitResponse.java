package com.omdasoft.orderonline.gwt.order.client.company.request;

import net.customware.gwt.dispatch.shared.Result;

import com.omdasoft.orderonline.gwt.order.client.enterprise.model.EnterpriseVo;

public class CompanyInitResponse implements Result{
	private EnterpriseVo enterprise;

	public EnterpriseVo getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(EnterpriseVo enterprise) {
		this.enterprise = enterprise;
	}

	public CompanyInitResponse() {

	}
}
