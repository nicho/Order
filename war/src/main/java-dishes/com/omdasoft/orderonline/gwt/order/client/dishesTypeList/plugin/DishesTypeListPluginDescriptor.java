/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesTypeList.plugin;

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
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.editor.DishesTypeListEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.MenuConstants;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class DishesTypeListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final DishesTypeListPlugin userPlugin;
	final DishesTypeListEditorDescriptor dishesTypeListRegisterEditorDescriptor;

	@Inject
	public DishesTypeListPluginDescriptor(
			final DishesTypeListEditorDescriptor dishesTypeListRegisterEditorDescriptor) {
		this.dishesTypeListRegisterEditorDescriptor = dishesTypeListRegisterEditorDescriptor;
		userPlugin = new DishesTypeListPlugin(this);

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
						return MenuConstants.MENU_ORDER_DISHESTYPELIST_EDIT;
					}

					@Override
					public String getMenuId() {
						return DishesTypeListConstants.MENU_DISHESTYPELIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "菜品类别管理";
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										DishesTypeListConstants.EDITOR_DISHESTYPELIST_SEARCH,
										"EDITOR_DISHESTYPELIST_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DishesTypeListPluginDescriptor.this;
			}

		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return dishesTypeListRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DishesTypeListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return DishesTypeListConstants.PLUGIN_DISHESTYPELIST;
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
