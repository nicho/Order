package com.omdasoft.orderonline.model.order;

public enum OrderStatus {

	UNHANDLED("待处理"),

	SUCCESS("成功"), FAILURE("失败"), NOTCONSUMPR("未消费"), HASCONSUMER("已消费"), 
	HASCANCEL("已取消待读"), ALREADYREAD("已取消已读");
	private final String displayName;

	OrderStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
