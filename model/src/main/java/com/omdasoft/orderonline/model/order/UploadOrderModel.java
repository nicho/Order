package com.omdasoft.orderonline.model.order;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class UploadOrderModel   implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderid;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

}
