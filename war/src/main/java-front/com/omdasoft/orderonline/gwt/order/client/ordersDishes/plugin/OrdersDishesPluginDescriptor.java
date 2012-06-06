/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.ordersDishes.plugin;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.Extension;
import com.omdasoft.orderonline.gwt.order.client.core.ExtensionPoint;
import com.omdasoft.orderonline.gwt.order.client.core.Plugin;
import com.omdasoft.orderonline.gwt.order.client.core.PluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.editor.OrdersDishesEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class OrdersDishesPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final OrdersDishesPlugin userPlugin;
	final OrdersDishesEditorDescriptor ordersDishesRegisterEditorDescriptor;

	@Inject
	public OrdersDishesPluginDescriptor(
			final OrdersDishesEditorDescriptor ordersDishesRegisterEditorDescriptor) {
		this.ordersDishesRegisterEditorDescriptor = ordersDishesRegisterEditorDescriptor;
		userPlugin = new OrdersDishesPlugin(this);

	

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return ordersDishesRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return OrdersDishesPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return OrdersDishesConstants.PLUGIN_ORDERSDISHES;
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
