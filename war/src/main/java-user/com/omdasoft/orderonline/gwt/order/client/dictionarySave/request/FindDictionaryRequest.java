/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dictionarySave.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class FindDictionaryRequest implements Action<FindDictionaryResponse> {

	
	private String dictionaryId;







	public String getDictionaryId() {
		return dictionaryId;
	}







	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}







	public FindDictionaryRequest() {
	}




}
