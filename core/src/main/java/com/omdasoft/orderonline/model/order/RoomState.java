package com.omdasoft.orderonline.model.order;

public enum RoomState {

	NOTRESERVATION("未订房"),
	HASRESERVATIONS("已订房");

	private final String displayName;

	RoomState(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
