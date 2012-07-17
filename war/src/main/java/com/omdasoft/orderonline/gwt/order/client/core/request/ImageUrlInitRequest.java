package com.omdasoft.orderonline.gwt.order.client.core.request;

import net.customware.gwt.dispatch.shared.Action;

public class ImageUrlInitRequest implements Action<ImageUrlInitResponse> {

	private String cid;
	private String did;

	public ImageUrlInitRequest() {

	}

	public ImageUrlInitRequest(String cid, String did) {
		this.cid = cid;
		this.did = did;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

}
