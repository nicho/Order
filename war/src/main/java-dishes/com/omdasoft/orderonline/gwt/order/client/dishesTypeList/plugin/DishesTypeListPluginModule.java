/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesTypeList.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.editor.DishesTypeListEditor;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.editor.DishesTypeListEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class DishesTypeListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DishesTypeListPluginDescriptor.class).in(Singleton.class);

		bind(DishesTypeListEditorDescriptor.class).in(Singleton.class);
		bind(DishesTypeListEditor.class);
	}

}
