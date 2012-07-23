/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class DishesTypeCopyRequest implements Action<DishesTypeCopyResponse> {

	private String deptId;




	public DishesTypeCopyRequest() {
	}


	/**
	 * 
	 * @param DishesListVo
	 */
	public DishesTypeCopyRequest(String deptId) {
		this.deptId=deptId;
	}


	public String getDeptId() {
		return deptId;
	}


	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


}
