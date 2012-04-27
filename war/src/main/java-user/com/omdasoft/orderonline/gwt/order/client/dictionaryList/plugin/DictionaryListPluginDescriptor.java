/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dictionaryList.plugin;

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
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.MenuConstants;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class DictionaryListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final DictionaryListPlugin userPlugin;
	final DictionaryListEditorDescriptor dictionaryListRegisterEditorDescriptor;

	@Inject
	public DictionaryListPluginDescriptor(
			final DictionaryListEditorDescriptor dictionaryListRegisterEditorDescriptor) {
		this.dictionaryListRegisterEditorDescriptor = dictionaryListRegisterEditorDescriptor;
		userPlugin = new DictionaryListPlugin(this);

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
						return MenuConstants.MENU_ORDER_DICTIONARYLIST_EDIT;
					}

					@Override
					public String getMenuId() {
						return DictionaryListConstants.MENU_DICTIONARYLIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "口味管理";
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										DictionaryListConstants.EDITOR_DICTIONARYLIST_SEARCH,
										"EDITOR_DICTIONARYLIST_SEARCH_DO_ID", 1);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DictionaryListPluginDescriptor.this;
			}

		});
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
						return MenuConstants.MENU_ORDER_DICTIONARYLIST_EDIT;
					}

					@Override
					public String getMenuId() {
						return DictionaryListConstants.MENU_DICTIONARYLIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "单位管理";
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										DictionaryListConstants.EDITOR_DICTIONARYLIST_SEARCH,
										"EDITOR_DICTIONARYLIST_SEARCH_DO_ID", 2);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DictionaryListPluginDescriptor.this;
			}

		});
		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return dictionaryListRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DictionaryListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return DictionaryListConstants.PLUGIN_DICTIONARYLIST;
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
