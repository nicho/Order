package com.omdasoft.orderonline.gwt.order.client.userView.model;

import java.io.Serializable;
import java.util.Date;

public class UserWinClient implements Serializable, Comparable<UserWinClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;
	
	String rewardName;
	Date winTime;
	String presentedName;

	public String getRewardName() {
		return rewardName;
	}
	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}
	public Date getWinTime() {
		return winTime;
	}
	public void setWinTime(Date winTime) {
		this.winTime = winTime;
	}
	public String getPresentedName() {
		return presentedName;
	}
	public void setPresentedName(String presentedName) {
		this.presentedName = presentedName;
	}
	public UserWinClient() {

	}
	@Override
	public int compareTo(UserWinClient o) {
		// TODO Auto-generated method stub
		return 0;
	}



}
