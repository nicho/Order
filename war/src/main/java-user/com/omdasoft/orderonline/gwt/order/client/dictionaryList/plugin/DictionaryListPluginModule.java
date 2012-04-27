/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dictionaryList.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.editor.DictionaryListEditor;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.editor.DictionaryListEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class DictionaryListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DictionaryListPluginDescriptor.class).in(Singleton.class);

		bind(DictionaryListEditorDescriptor.class).in(Singleton.class);
		bind(DictionaryListEditor.class);
	}

}
