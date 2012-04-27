package com.omdasoft.orderonline.gwt.order.client.enterprise.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.enterprise.model.EnterpriseVo;
import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

public class EnterpriseRequest implements Action<EnterpriseResponse> {

	private EnterpriseVo enterprisevo;
	private UserSession userSession;

	public EnterpriseRequest() {
	}

	public EnterpriseRequest(EnterpriseVo enterprisevo, UserSession userSession) {
		this.enterprisevo = enterprisevo;
		this.userSession = userSession;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public EnterpriseVo getEnterprise() {
		return enterprisevo;
	}

	public void setEnterprise(EnterpriseVo enterprisevo) {
		this.enterprisevo = enterprisevo;
	}
}
