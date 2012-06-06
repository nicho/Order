/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderIndex.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.editor.OrderIndexEditor;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.editor.OrderIndexEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class OrderIndexPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderIndexPluginDescriptor.class).in(Singleton.class);

		bind(OrderIndexEditorDescriptor.class).in(Singleton.class);
		bind(OrderIndexEditor.class);
	}

}
