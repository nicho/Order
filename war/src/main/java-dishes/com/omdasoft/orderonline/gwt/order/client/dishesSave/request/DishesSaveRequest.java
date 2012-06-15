/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesSave.request;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class DishesSaveRequest implements Action<DishesSaveResponse> {

	
	private UserSession session;

	private String id;
	private String name;
	private String dishesType;	
	private String description;	
	private String photo;	
	private String price;
	private int status;
	private String taste;

	public String getTaste() {
		return taste;
	}



	public void setTaste(String taste) {
		this.taste = taste;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}
	public String getDishesType() {
		return dishesType;
	}



	public void setDishesType(String dishesType) {
		this.dishesType = dishesType;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
		this.photo = photo;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
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



	public DishesSaveRequest() {
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
	public DishesSaveRequest(UserSession session) {
		this.session=session;
	}


}
