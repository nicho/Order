package com.omdasoft.orderonline.gwt.order.client.register.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.enterprise.model.EnterpriseVo;

public class RegisterRequest implements Action<RegisterResponse> {

	private EnterpriseVo enterprisevo;
	
   
	public RegisterRequest() {
	}

	public RegisterRequest(EnterpriseVo enterprisevo) {
		this.enterprisevo = enterprisevo;
				
	}

	public EnterpriseVo getEnterprise() {
		return enterprisevo;
	}

	public void setEnterprise(EnterpriseVo enterprisevo) {
		this.enterprisevo = enterprisevo;
	}
}
