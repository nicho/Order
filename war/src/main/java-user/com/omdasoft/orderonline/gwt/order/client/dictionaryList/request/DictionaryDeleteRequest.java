/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dictionaryList.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class DictionaryDeleteRequest implements Action<DictionaryDeleteResponse> {

	private String id;
	private UserSession session;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public DictionaryDeleteRequest() {
	}

	
	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	/**
	 * 
	 * @param DishesListVo
	 */
	public DictionaryDeleteRequest(String id,UserSession session) {
		this.id=id;
		this.session=session;
	}


}
