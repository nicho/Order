/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userList.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.userList.editor.UserListEditor;
import com.omdasoft.orderonline.gwt.order.client.userList.editor.UserListEditorDescriptor;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class UserListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UserListPluginDescriptor.class).in(Singleton.class);

		bind(UserListEditorDescriptor.class).in(Singleton.class);
		bind(UserListEditor.class);
	}

}
