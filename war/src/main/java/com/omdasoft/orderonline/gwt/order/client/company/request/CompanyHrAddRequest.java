package com.omdasoft.orderonline.gwt.order.client.company.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

public class CompanyHrAddRequest implements Action<CompanyHrAddResponse>{
	
	public CompanyHrAddRequest(String companyId,UserSession session){
		this.companyId=companyId;
		this.session=session;
	}
	public CompanyHrAddRequest(){
		 
	}
	
	private String companyId;
	private UserSession session;
	

	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
}
