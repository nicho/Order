package com.omdasoft.orderonline.gwt.order.client.core.request;

import net.customware.gwt.dispatch.shared.Result;

public class ImageUrlInitResponse implements Result {

	String url;
	String jbossname;

	public String getJbossname() {
		return jbossname;
	}

	public void setJbossname(String jbossname) {
		this.jbossname = jbossname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ImageUrlInitResponse() {

	}

}
