package com.omdasoft.orderonline.model.order;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class DishesTypeModel  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;	
	private String name;
	private String rid;

	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	



}
