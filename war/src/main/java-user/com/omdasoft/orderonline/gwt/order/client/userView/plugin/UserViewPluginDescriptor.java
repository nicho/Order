/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userView.plugin;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.Extension;
import com.omdasoft.orderonline.gwt.order.client.core.ExtensionPoint;
import com.omdasoft.orderonline.gwt.order.client.core.Plugin;
import com.omdasoft.orderonline.gwt.order.client.core.PluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;
import com.omdasoft.orderonline.gwt.order.client.userView.editor.UserViewEditorDescriptor;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class UserViewPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final UserViewPlugin userPlugin;
	final UserViewEditorDescriptor staffViewRegisterEditorDescriptor;

	@Inject
	public UserViewPluginDescriptor(
			final UserViewEditorDescriptor staffViewRegisterEditorDescriptor) {
		this.staffViewRegisterEditorDescriptor = staffViewRegisterEditorDescriptor;
		userPlugin = new UserViewPlugin(this);

//		/**
//		 * Search user menu
//		 */
//		ext.add(new Extension() {
//
//			@Override
//			public String getExtensionPointId() {
//				return PluginConstants.MENU;
//			}
//
//			@Override
//			public Object getInstance() {
//				return new MenuItem() {
//
//					@Override
//					public int getOrder() {
//						return MenuConstants.MENU_ORDER_STAFFVIEW_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return UserViewConstants.MENU_STAFFVIEW_SEARCH;
//					}
//
//					@Override
//					public String getParentMenuId() {
//						return null;
//					}
//
//					@Override
//					public String getTitle() {
//						return "员工列表";
//					}
//
//					@Override
//					public void execute() {
//						Platform.getInstance()
//								.getEditorRegistry()
//								.openEditor(
//										UserViewConstants.EDITOR_STAFFVIEW_SEARCH,
//										"EDITOR_STAFFVIEW_SEARCH_DO_ID", null);
//					}
//
//					@Override
//					public Image getIcon() {
//						return null;
//					}
//
//				};
//			}
//
//			@Override
//			public PluginDescriptor getPluginDescriptor() {
//				return UserViewPluginDescriptor.this;
//			}
//
//		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return staffViewRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return UserViewPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return UserViewConstants.PLUGIN_STAFFVIEW;
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
