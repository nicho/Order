/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesSave.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.editor.DishesSaveEditor;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.editor.DishesSaveEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class DishesSavePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DishesSavePluginDescriptor.class).in(Singleton.class);

		bind(DishesSaveEditorDescriptor.class).in(Singleton.class);
		bind(DishesSaveEditor.class);
	}

}
