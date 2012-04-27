package com.omdasoft.orderonline.gwt.order.client.restaurantList.model;

import java.io.Serializable;

public class OrganicationClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1198342894580577960L;

	protected String id;

	protected String name;


	public OrganicationClient() {

	}

	public OrganicationClient(String id, String name) {
		this.id = id;
		this.name = name;
	}



	@Override
	public String toString() {
		return "OrganicationClient [id=" + id + ", name=" + name + "]";
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
