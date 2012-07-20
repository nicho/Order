/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.companyAdd.plugin;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.editor.CompanyAddEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.core.Extension;
import com.omdasoft.orderonline.gwt.order.client.core.ExtensionPoint;
import com.omdasoft.orderonline.gwt.order.client.core.Plugin;
import com.omdasoft.orderonline.gwt.order.client.core.PluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;

/**
 * @author sunny
 */
public class CompanyAddPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final CompanyAddPlugin userPlugin;
	final CompanyAddEditorDescriptor companyAddRegisterEditorDescriptor;

	@Inject
	public CompanyAddPluginDescriptor(
			final CompanyAddEditorDescriptor companyAddRegisterEditorDescriptor) {
		this.companyAddRegisterEditorDescriptor = companyAddRegisterEditorDescriptor;
		userPlugin = new CompanyAddPlugin(this);

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
//						return MenuConstants.MENU_ORDER_COMPANYADD_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return CompanyAddConstants.MENU_COMPANYADD_SEARCH;
//					}
//
//					@Override
//					public String getParentMenuId() {
//						return null;
//					}
//
//					@Override
//					public String getTitle() {
//						return "创建新企业端";
//					}
//
//					@Override
//					public void execute() {
//						Platform.getInstance()
//								.getEditorRegistry()
//								.openEditor(
//										CompanyAddConstants.EDITOR_COMPANYADD_SEARCH,
//										"EDITOR_COMPANYADD_SEARCH_DO_ID", null);
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
//				return CompanyAddPluginDescriptor.this;
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
				return companyAddRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return CompanyAddPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return CompanyAddConstants.PLUGIN_COMPANYADD;
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
