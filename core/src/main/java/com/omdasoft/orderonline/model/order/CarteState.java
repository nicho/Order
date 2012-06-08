package com.omdasoft.orderonline.model.order;

public enum CarteState {

	NOTPOINT("未点"),
	WAITREAD("已点待读"), ALREADYREAD("已点已读");

	private final String displayName;

	CarteState(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
