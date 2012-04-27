/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dictionarySave.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.editor.DictionarySaveEditor;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.editor.DictionarySaveEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class DictionarySavePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DictionarySavePluginDescriptor.class).in(Singleton.class);

		bind(DictionarySaveEditorDescriptor.class).in(Singleton.class);
		bind(DictionarySaveEditor.class);
	}

}
