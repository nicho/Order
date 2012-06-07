package com.omdasoft.orderonline.model.order;

public enum OrderSource {

	ONLINE("网上"),
	PHONE("电话");

	private final String displayName;

	OrderSource(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
