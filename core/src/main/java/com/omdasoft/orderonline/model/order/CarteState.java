package com.omdasoft.orderonline.model.order;

public enum CarteState {

	NOTPOINT("未点"),
	ALREADYPOINTS("已点"), WAITREAD("待读"), ALREADYREAD("已读");

	private final String displayName;

	CarteState(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
