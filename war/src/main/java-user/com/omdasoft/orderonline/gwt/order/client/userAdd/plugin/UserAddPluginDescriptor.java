/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userAdd.plugin;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.Extension;
import com.omdasoft.orderonline.gwt.order.client.core.ExtensionPoint;
import com.omdasoft.orderonline.gwt.order.client.core.Plugin;
import com.omdasoft.orderonline.gwt.order.client.core.PluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;
import com.omdasoft.orderonline.gwt.order.client.userAdd.editor.UserAddEditorDescriptor;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class UserAddPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final UserAddPlugin userPlugin;
	final UserAddEditorDescriptor staffAddRegisterEditorDescriptor;

	@Inject
	public UserAddPluginDescriptor(
			final UserAddEditorDescriptor staffAddRegisterEditorDescriptor) {
		this.staffAddRegisterEditorDescriptor = staffAddRegisterEditorDescriptor;
		userPlugin = new UserAddPlugin(this);

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
//						return MenuConstants.MENU_ORDER_STAFFADD_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return StaffAddConstants.MENU_STAFFADD_SEARCH;
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
//										StaffAddConstants.EDITOR_STAFFADD_SEARCH,
//										"EDITOR_STAFFADD_SEARCH_DO_ID", null);
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
//				return StaffAddPluginDescriptor.this;
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
				return staffAddRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return UserAddPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return UserAddConstants.PLUGIN_STAFFADD;
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
