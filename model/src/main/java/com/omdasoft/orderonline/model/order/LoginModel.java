package com.omdasoft.orderonline.model.order;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class LoginModel  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tokenid;
	private String url;
	public String getTokenid() {
		return tokenid;
	}
	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}





}
