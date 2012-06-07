package com.omdasoft.orderonline.model.order;

public enum OrderType {

	RESERVATIONS("订座"),
	FASTFOOD("快餐"), PACKAGED("打包");

	private final String displayName;

	OrderType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
