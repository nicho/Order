package com.omdasoft.orderonline.gwt.order.client.dictionaryList.model;

import java.io.Serializable;

public class DictionaryListClient implements Serializable, Comparable<DictionaryListClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;
	
	String id;
	String name;
	String indexNo;
	int dictionaryType;
	

	public String getIndexNo() {
		return indexNo;
	}
	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}
	public int getDictionaryType() {
		return dictionaryType;
	}
	public void setDictionaryType(int dictionaryType) {
		this.dictionaryType = dictionaryType;
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
	public DictionaryListClient() {

	}
	@Override
	public int compareTo(DictionaryListClient o) {
		// TODO Auto-generated method stub
		return 0;
	}



}
