package com.omdasoft.orderonline.gwt.order.client.restaurantList.choose;

import java.util.List;

import com.google.gwt.event.shared.EventHandler;

public interface ChooseLeaderHandler extends EventHandler {
	void chosenLeader(List<StaffClient> list);
}
