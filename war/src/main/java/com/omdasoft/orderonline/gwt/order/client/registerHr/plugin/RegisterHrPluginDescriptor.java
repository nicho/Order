package com.omdasoft.orderonline.gwt.order.client.registerHr.plugin;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.Extension;
import com.omdasoft.orderonline.gwt.order.client.core.ExtensionPoint;
import com.omdasoft.orderonline.gwt.order.client.core.Plugin;
import com.omdasoft.orderonline.gwt.order.client.core.PluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.registerHr.editor.RegisterHrEditorDescriptor;

public class RegisterHrPluginDescriptor implements PluginDescriptor {
	final static Set<Extension> extensions = new HashSet<Extension>();
	final static String PLUGIN_ID = RegisterHrConstants.PLUGIN_REGISTERHR;
	final RegisterHrPlugin plugin;
	final RegisterHrEditorDescriptor descriptor;

	@Inject
	public RegisterHrPluginDescriptor(
			final RegisterHrEditorDescriptor editorDesc) {
		this.descriptor = editorDesc;
		plugin = new RegisterHrPlugin(this);
//		extensions.add(new Extension() {
//			public String getExtensionPointId() {
//				return PluginConstants.MENU;
//			}
//
//			public Object getInstance() {
//				return new MenuItem() {
//					public void execute() {
//						Platform.getInstance()
//								.getEditorRegistry()
//								.openEditor(
//										RegisterHrConstants.EDITOR_REGISTERHR_EDIT,
//										"RegisterHrInstanceID", null);
//					}
//
//					public Image getIcon() {
//						return null;
//					}
//
//					public String getMenuId() {
//						return RegisterHrConstants.MENU_REGISTERHR_EDIT;
//					}
//
//					public int getOrder() {
//						return 0;
//					}
//
//					public String getParentMenuId() {
//						return null;
//					}
//
//					public String getTitle() {
//						return "公司资料";
//					}
//
//				};
//			}
//
//			public PluginDescriptor getPluginDescriptor() {
//				return RegisterHrPluginDescriptor.this;
//			}
//
//		});

		extensions.add(new Extension() {

			public String getExtensionPointId() {
				return "core.editor";
			}

			public Object getInstance() {
				return descriptor;
			}

			public PluginDescriptor getPluginDescriptor() {
				return RegisterHrPluginDescriptor.this;
			}

		});

	}

	public Set<ExtensionPoint> getExtensionPoints() {
		return null;
	}

	public Set<Extension> getExtensions() {
		return extensions;
	}

	public Plugin getInstance() {
		return plugin;
	}

	public String getPluginId() {
		return PLUGIN_ID;
	}

}
