/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userList.plugin;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.Extension;
import com.omdasoft.orderonline.gwt.order.client.core.ExtensionPoint;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.core.Plugin;
import com.omdasoft.orderonline.gwt.order.client.core.PluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.MenuItem;
import com.omdasoft.orderonline.gwt.order.client.plugin.MenuConstants;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;
import com.omdasoft.orderonline.gwt.order.client.userList.editor.UserListEditorDescriptor;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class UserListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final UserListPlugin userPlugin;
	final UserListEditorDescriptor userListRegisterEditorDescriptor;

	@Inject
	public UserListPluginDescriptor(
			final UserListEditorDescriptor userListRegisterEditorDescriptor) {
		this.userListRegisterEditorDescriptor = userListRegisterEditorDescriptor;
		userPlugin = new UserListPlugin(this);

		/**
		 * Search user menu
		 */
		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.MENU;
			}

			@Override
			public Object getInstance() {
				return new MenuItem() {

					@Override
					public int getOrder() {
						return MenuConstants.MENU_ORDER_USERLIST_EDIT;
					}

					@Override
					public String getMenuId() {
						return UserListConstants.MENU_USERLIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "用户管理";
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										UserListConstants.EDITOR_USERLIST_SEARCH,
										"EDITOR_USERLIST_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return UserListPluginDescriptor.this;
			}

		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return userListRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return UserListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return UserListConstants.PLUGIN_USERLIST;
	}

	@Override
	public Plugin getInstance() {
		return userPlugin;
	}

	@Override
	public Set<ExtensionPoint> getExtensionPoints() {
		return null;
	}

	@Override
	public Set<Extension> getExtensions() {
		return ext;
	}

}
