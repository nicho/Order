package com.omdasoft.orderonline.gwt.order.client.core;

public interface Plugin {

	/**
	 * Should be a simple delegate to getPluginDescriptor().getPluginId()
	 * 
	 * @return
	 */
	String getPluginId();

	PluginDescriptor getPluginDescriptor();

	void activate();

	boolean isActivated();

}
