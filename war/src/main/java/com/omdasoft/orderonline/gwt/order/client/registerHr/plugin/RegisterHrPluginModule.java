package com.omdasoft.orderonline.gwt.order.client.registerHr.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.registerHr.editor.RegisterHrEditor;
import com.omdasoft.orderonline.gwt.order.client.registerHr.editor.RegisterHrEditorDescriptor;

public class RegisterHrPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RegisterHrPluginDescriptor.class).in(Singleton.class);
		bind(RegisterHrEditorDescriptor.class).in(Singleton.class);
		bind(RegisterHrEditor.class);


	}

}
