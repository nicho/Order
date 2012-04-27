/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderList.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.orderList.editor.OrderListEditor;
import com.omdasoft.orderonline.gwt.order.client.orderList.editor.OrderListEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class OrderListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderListPluginDescriptor.class).in(Singleton.class);

		bind(OrderListEditorDescriptor.class).in(Singleton.class);
		bind(OrderListEditor.class);
	}

}
