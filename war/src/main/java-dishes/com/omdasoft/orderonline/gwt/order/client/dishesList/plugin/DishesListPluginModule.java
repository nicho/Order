/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesList.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.dishesList.editor.DishesListEditor;
import com.omdasoft.orderonline.gwt.order.client.dishesList.editor.DishesListEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class DishesListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DishesListPluginDescriptor.class).in(Singleton.class);

		bind(DishesListEditorDescriptor.class).in(Singleton.class);
		bind(DishesListEditor.class);
	}

}
