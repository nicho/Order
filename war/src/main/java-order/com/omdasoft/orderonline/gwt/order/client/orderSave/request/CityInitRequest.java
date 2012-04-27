/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderSave.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class CityInitRequest implements Action<CityInitResponse> {

	String corpId;
	String cityName;
	
	

	public String getCityName() {
		return cityName;
	}



	public void setCityName(String cityName) {
		this.cityName = cityName;
	}



	public String getCorpId() {
		return corpId;
	}



	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}



	public CityInitRequest() {
	}

	public CityInitRequest(String corpId,String cityName) {
		this.corpId=corpId;
		this.cityName=cityName;
	}



}
