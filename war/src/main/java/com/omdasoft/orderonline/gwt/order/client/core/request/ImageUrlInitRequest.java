package com.omdasoft.orderonline.gwt.order.client.core.request;

import net.customware.gwt.dispatch.shared.Action;

public class ImageUrlInitRequest implements Action<ImageUrlInitResponse> {

	private String staffId;

	public ImageUrlInitRequest() {

	}

	public ImageUrlInitRequest(String staffId) {
		this.staffId = staffId;

	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	

}
