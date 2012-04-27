/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderSave.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.orderSave.editor.OrderSaveEditor;
import com.omdasoft.orderonline.gwt.order.client.orderSave.editor.OrderSaveEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class OrderSavePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderSavePluginDescriptor.class).in(Singleton.class);

		bind(OrderSaveEditorDescriptor.class).in(Singleton.class);
		bind(OrderSaveEditor.class);
	}

}
