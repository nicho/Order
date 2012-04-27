/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderSave.plugin;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.Extension;
import com.omdasoft.orderonline.gwt.order.client.core.ExtensionPoint;
import com.omdasoft.orderonline.gwt.order.client.core.Plugin;
import com.omdasoft.orderonline.gwt.order.client.core.PluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderSave.editor.OrderSaveEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class OrderSavePluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final OrderSavePlugin userPlugin;
	final OrderSaveEditorDescriptor orderSaveRegisterEditorDescriptor;

	@Inject
	public OrderSavePluginDescriptor(
			final OrderSaveEditorDescriptor orderSaveRegisterEditorDescriptor) {
		this.orderSaveRegisterEditorDescriptor = orderSaveRegisterEditorDescriptor;
		userPlugin = new OrderSavePlugin(this);

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
//						return MenuConstants.MENU_ORDER_ORDERSAVE_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return OrderSaveConstants.MENU_ORDERSAVE_SEARCH;
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
//										OrderSaveConstants.EDITOR_ORDERSAVE_SEARCH,
//										"EDITOR_ORDERSAVE_SEARCH_DO_ID", null);
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
//				return OrderSavePluginDescriptor.this;
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
				return orderSaveRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return OrderSavePluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return OrderSaveConstants.PLUGIN_ORDERSAVE;
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
