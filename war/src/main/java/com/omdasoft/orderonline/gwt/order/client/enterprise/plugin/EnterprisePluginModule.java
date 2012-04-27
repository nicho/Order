package com.omdasoft.orderonline.gwt.order.client.enterprise.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.enterprise.editor.EnterpriseEditor;
import com.omdasoft.orderonline.gwt.order.client.enterprise.editor.EnterpriseEditorDescriptor;

public class EnterprisePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EnterprisePluginDescriptor.class).in(Singleton.class);
		bind(EnterpriseEditorDescriptor.class).in(Singleton.class);
		bind(EnterpriseEditor.class);


	}

}
