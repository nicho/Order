/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dictionarySave.plugin;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.Extension;
import com.omdasoft.orderonline.gwt.order.client.core.ExtensionPoint;
import com.omdasoft.orderonline.gwt.order.client.core.Plugin;
import com.omdasoft.orderonline.gwt.order.client.core.PluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.editor.DictionarySaveEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class DictionarySavePluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final DictionarySavePlugin userPlugin;
	final DictionarySaveEditorDescriptor dictionarySaveRegisterEditorDescriptor;

	@Inject
	public DictionarySavePluginDescriptor(
			final DictionarySaveEditorDescriptor dictionarySaveRegisterEditorDescriptor) {
		this.dictionarySaveRegisterEditorDescriptor = dictionarySaveRegisterEditorDescriptor;
		userPlugin = new DictionarySavePlugin(this);

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
//					public int getDictionary() {
//						return MenuConstants.MENU_ORDER_DICTIONARYSAVE_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return DictionarySaveConstants.MENU_DICTIONARYSAVE_SEARCH;
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
//										DictionarySaveConstants.EDITOR_DICTIONARYSAVE_SEARCH,
//										"EDITOR_DICTIONARYSAVE_SEARCH_DO_ID", null);
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
//				return DictionarySavePluginDescriptor.this;
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
				return dictionarySaveRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DictionarySavePluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return DictionarySaveConstants.PLUGIN_DICTIONARYSAVE;
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
