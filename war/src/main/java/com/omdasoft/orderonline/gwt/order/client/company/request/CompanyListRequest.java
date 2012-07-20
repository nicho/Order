package com.omdasoft.orderonline.gwt.order.client.company.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.company.model.CompanySearchVo;
import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

public class CompanyListRequest implements Action<CompanyListResponse> {

	private CompanySearchVo companyVo;
	private UserSession userSession;

	public CompanyListRequest() {
	}

	public CompanyListRequest(CompanySearchVo companyVo, UserSession userSession) {
		this.companyVo = companyVo;
		this.userSession = userSession;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public CompanySearchVo getCompanyVo() {
		return companyVo;
	}

	public void setCompanyVo(CompanySearchVo companyVo) {
		this.companyVo = companyVo;
	}
}
