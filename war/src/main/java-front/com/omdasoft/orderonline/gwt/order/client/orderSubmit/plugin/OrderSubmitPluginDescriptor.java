/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderSubmit.plugin;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.Extension;
import com.omdasoft.orderonline.gwt.order.client.core.ExtensionPoint;
import com.omdasoft.orderonline.gwt.order.client.core.Plugin;
import com.omdasoft.orderonline.gwt.order.client.core.PluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.editor.OrderSubmitEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class OrderSubmitPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final OrderSubmitPlugin userPlugin;
	final OrderSubmitEditorDescriptor orderSubmitRegisterEditorDescriptor;

	@Inject
	public OrderSubmitPluginDescriptor(
			final OrderSubmitEditorDescriptor orderSubmitRegisterEditorDescriptor) {
		this.orderSubmitRegisterEditorDescriptor = orderSubmitRegisterEditorDescriptor;
		userPlugin = new OrderSubmitPlugin(this);

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
//						return MenuConstants.MENU_ORDER_ORDERSUBMIT_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return OrderSubmitConstants.MENU_ORDERSUBMIT_SEARCH;
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
//										OrderSubmitConstants.EDITOR_ORDERSUBMIT_SEARCH,
//										"EDITOR_ORDERSUBMIT_SEARCH_DO_ID", null);
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
//				return OrderSubmitPluginDescriptor.this;
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
				return orderSubmitRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return OrderSubmitPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return OrderSubmitConstants.PLUGIN_ORDERSUBMIT;
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
