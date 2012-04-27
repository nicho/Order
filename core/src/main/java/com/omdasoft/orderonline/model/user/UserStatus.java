package com.omdasoft.orderonline.model.user;


/**
 * The status of account.
 * 
 * @author yanxin
 * @since 1.0.0
 * 
 */
public enum UserStatus {

	Active("已启用"), Inactive("已停用");

	private UserStatus(String displayName) {
		this.displayName = displayName;
	}

	String displayName;

	public String getDisplayName() {
		return this.displayName;
	}
}
