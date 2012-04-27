/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userView.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.userView.editor.UserViewEditor;
import com.omdasoft.orderonline.gwt.order.client.userView.editor.UserViewEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class UserViewPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UserViewPluginDescriptor.class).in(Singleton.class);

		bind(UserViewEditorDescriptor.class).in(Singleton.class);
		bind(UserViewEditor.class);
	}

}
