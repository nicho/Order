/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderSubmit.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.editor.OrderSubmitEditor;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.editor.OrderSubmitEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class OrderSubmitPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderSubmitPluginDescriptor.class).in(Singleton.class);

		bind(OrderSubmitEditorDescriptor.class).in(Singleton.class);
		bind(OrderSubmitEditor.class);
	}

}
