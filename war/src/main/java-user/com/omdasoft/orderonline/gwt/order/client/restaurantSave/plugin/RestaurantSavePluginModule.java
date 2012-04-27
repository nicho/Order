/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.restaurantSave.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.editor.RestaurantSaveEditor;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.editor.RestaurantSaveEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class RestaurantSavePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RestaurantSavePluginDescriptor.class).in(Singleton.class);

		bind(RestaurantSaveEditorDescriptor.class).in(Singleton.class);
		bind(RestaurantSaveEditor.class);
	}

}
