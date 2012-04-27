/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderView.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.orderView.editor.OrderViewEditor;
import com.omdasoft.orderonline.gwt.order.client.orderView.editor.OrderViewEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class OrderViewPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderViewPluginDescriptor.class).in(Singleton.class);

		bind(OrderViewEditorDescriptor.class).in(Singleton.class);
		bind(OrderViewEditor.class);
	}

}
