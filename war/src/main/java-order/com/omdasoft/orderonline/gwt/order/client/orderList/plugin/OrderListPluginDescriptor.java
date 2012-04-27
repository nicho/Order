/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderList.plugin;

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
import com.omdasoft.orderonline.gwt.order.client.orderList.editor.OrderListEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.MenuConstants;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class OrderListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final OrderListPlugin userPlugin;
	final OrderListEditorDescriptor orderListRegisterEditorDescriptor;

	@Inject
	public OrderListPluginDescriptor(
			final OrderListEditorDescriptor orderListRegisterEditorDescriptor) {
		this.orderListRegisterEditorDescriptor = orderListRegisterEditorDescriptor;
		userPlugin = new OrderListPlugin(this);

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
						return MenuConstants.MENU_ORDER_ORDERLIST_EDIT;
					}

					@Override
					public String getMenuId() {
						return OrderListConstants.MENU_ORDERLIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "订单列表";
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										OrderListConstants.EDITOR_ORDERLIST_SEARCH,
										"EDITOR_ORDERLIST_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return OrderListPluginDescriptor.this;
			}

		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return orderListRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return OrderListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return OrderListConstants.PLUGIN_ORDERLIST;
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
