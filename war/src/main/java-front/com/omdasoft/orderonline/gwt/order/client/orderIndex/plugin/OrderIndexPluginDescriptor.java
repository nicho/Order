/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderIndex.plugin;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.Extension;
import com.omdasoft.orderonline.gwt.order.client.core.ExtensionPoint;
import com.omdasoft.orderonline.gwt.order.client.core.Plugin;
import com.omdasoft.orderonline.gwt.order.client.core.PluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.editor.OrderIndexEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class OrderIndexPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final OrderIndexPlugin userPlugin;
	final OrderIndexEditorDescriptor orderIndexRegisterEditorDescriptor;

	@Inject
	public OrderIndexPluginDescriptor(
			final OrderIndexEditorDescriptor orderIndexRegisterEditorDescriptor) {
		this.orderIndexRegisterEditorDescriptor = orderIndexRegisterEditorDescriptor;
		userPlugin = new OrderIndexPlugin(this);

	

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return orderIndexRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return OrderIndexPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return OrderIndexConstants.PLUGIN_ORDERINDEX;
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
