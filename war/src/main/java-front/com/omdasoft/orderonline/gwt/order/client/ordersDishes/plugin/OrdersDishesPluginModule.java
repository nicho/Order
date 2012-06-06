/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.ordersDishes.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.editor.OrdersDishesEditor;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.editor.OrdersDishesEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class OrdersDishesPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrdersDishesPluginDescriptor.class).in(Singleton.class);

		bind(OrdersDishesEditorDescriptor.class).in(Singleton.class);
		bind(OrdersDishesEditor.class);
	}

}
