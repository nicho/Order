/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesList.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class DishesCopyRequest implements Action<DishesCopyResponse> {

	private String deptId;




	public DishesCopyRequest() {
	}


	/**
	 * 
	 * @param DishesListVo
	 */
	public DishesCopyRequest(String deptId) {
		this.deptId=deptId;
	}


	public String getDeptId() {
		return deptId;
	}


	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


}
