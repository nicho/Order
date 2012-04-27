/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dictionarySave.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class DictionarySaveRequest implements Action<DictionarySaveResponse> {

	
	private UserSession session;
	private String name;
	private String id;
	private int dictionaryType;


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



	public DictionarySaveRequest() {
	}



	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	/**
	 * 
	 * @param OrderListVo
	 */
	public DictionarySaveRequest(UserSession session) {
		this.session=session;
	}


}
