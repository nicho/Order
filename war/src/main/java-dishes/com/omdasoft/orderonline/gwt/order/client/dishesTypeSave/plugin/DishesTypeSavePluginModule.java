/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.editor.DishesTypeSaveEditor;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.editor.DishesTypeSaveEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class DishesTypeSavePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DishesTypeSavePluginDescriptor.class).in(Singleton.class);

		bind(DishesTypeSaveEditorDescriptor.class).in(Singleton.class);
		bind(DishesTypeSaveEditor.class);
	}

}
