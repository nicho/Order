/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderView.plugin;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.Extension;
import com.omdasoft.orderonline.gwt.order.client.core.ExtensionPoint;
import com.omdasoft.orderonline.gwt.order.client.core.Plugin;
import com.omdasoft.orderonline.gwt.order.client.core.PluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderView.editor.OrderViewEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class OrderViewPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final OrderViewPlugin userPlugin;
	final OrderViewEditorDescriptor orderViewRegisterEditorDescriptor;

	@Inject
	public OrderViewPluginDescriptor(
			final OrderViewEditorDescriptor orderViewRegisterEditorDescriptor) {
		this.orderViewRegisterEditorDescriptor = orderViewRegisterEditorDescriptor;
		userPlugin = new OrderViewPlugin(this);

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
//						return MenuConstants.MENU_ORDER_ORDERVIEW_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return OrderViewConstants.MENU_ORDERVIEW_SEARCH;
//					}
//
//					@Override
//					public String getParentMenuId() {
//						return null;
//					}
//
//					@Override
//					public String getTitle() {
//						return "订单列表";
//					}
//
//					@Override
//					public void execute() {
//						Platform.getInstance()
//								.getEditorRegistry()
//								.openEditor(
//										OrderViewConstants.EDITOR_ORDERVIEW_SEARCH,
//										"EDITOR_ORDERVIEW_SEARCH_DO_ID", null);
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
//				return OrderViewPluginDescriptor.this;
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
				return orderViewRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return OrderViewPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return OrderViewConstants.PLUGIN_ORDERVIEW;
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
