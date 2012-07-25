/**
 * 
 */
package com.omdasoft.orderonline.model.user;

import com.omdasoft.orderonline.model.user.UserRole;

public class UserContext {

	/**
	 * Returns the user ID. This is the ID of the HrUser. If no user is
	 * identified, this method returns <code>null</code>.
	 * 
	 * @return
	 */
	public String userId;

	/**
	 * Which corporation the user owns.
	 * 
	 * @return
	 */
	public String corporationId;
	public String deptId;

	public UserRole[] userRoles;
	public UserRole lastRole;
	public String loginName;

	public UserRole getLastRole() {
		return lastRole;
	}

	public void setLastRole(UserRole lastRole) {
		this.lastRole = lastRole;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public UserRole[] getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRole[] userRoles) {
		this.userRoles = userRoles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
