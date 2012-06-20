package com.omdasoft.orderonline.gwt.order.client.core.request;

import net.customware.gwt.dispatch.shared.Action;

public class ImageUrlInitRequest implements Action<ImageUrlInitResponse> {

	private String cid;

	public ImageUrlInitRequest() {

	}

	public ImageUrlInitRequest(String cid) {
		this.cid = cid;

	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
