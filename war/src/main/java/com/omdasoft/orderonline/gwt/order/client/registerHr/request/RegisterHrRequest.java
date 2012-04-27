package com.omdasoft.orderonline.gwt.order.client.registerHr.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.registerHr.model.HrVo;

public class RegisterHrRequest implements Action<RegisterHrResponse> {

	private HrVo hrvo;


	
   
	public HrVo getHrvo() {
		return hrvo;
	}

	public void setHrvo(HrVo hrvo) {
		this.hrvo = hrvo;
	}

	public RegisterHrRequest() {
	}

	public RegisterHrRequest(HrVo hrvo) {
		this.hrvo = hrvo;
				
	}

	
}
