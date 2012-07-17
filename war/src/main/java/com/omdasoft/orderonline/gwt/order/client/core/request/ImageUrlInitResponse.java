package com.omdasoft.orderonline.gwt.order.client.core.request;

import net.customware.gwt.dispatch.shared.Result;

public class ImageUrlInitResponse implements Result {

	String url;
	String jbossname;
	String corpId;
	String deptId;
	String uploadUrl;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

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
