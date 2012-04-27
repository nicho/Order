package com.omdasoft.orderonline.gwt.order.client.core.ui.event;

import com.google.gwt.event.shared.GwtEvent;
import com.omdasoft.orderonline.gwt.order.client.core.ui.MenuItem;

public class MenuClickEvent extends GwtEvent<MenuClickHandler> {

	private static Type<MenuClickHandler> TYPE = new Type<MenuClickHandler>();

	final MenuItem menuItem;

	public static Type<MenuClickHandler> getType() {
		return TYPE;
	}

	public MenuClickEvent(MenuItem item) {
		this.menuItem = item;
	}

	@Override
	protected void dispatch(MenuClickHandler handler) {
		handler.onClick(menuItem);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MenuClickHandler> getAssociatedType() {
		return TYPE;
	}

}
