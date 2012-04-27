/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.restaurantList.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.editor.RestaurantListEditor;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.editor.RestaurantListEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class RestaurantListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RestaurantListPluginDescriptor.class).in(Singleton.class);

		bind(RestaurantListEditorDescriptor.class).in(Singleton.class);
		bind(RestaurantListEditor.class);
	}

}
