/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userAdd.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.userAdd.editor.UserAddEditor;
import com.omdasoft.orderonline.gwt.order.client.userAdd.editor.UserAddEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class UserAddPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UserAddPluginDescriptor.class).in(Singleton.class);

		bind(UserAddEditorDescriptor.class).in(Singleton.class);
		bind(UserAddEditor.class);
	}

}
