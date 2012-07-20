package com.omdasoft.orderonline.gwt.order.client.company.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.company.editor.CompanyListEditor;
import com.omdasoft.orderonline.gwt.order.client.company.editor.CompanyListEditorDescriptor;

public class CompanyListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {

		
		bind(CompanyListPluginDescriptor.class).in(Singleton.class);

		bind(CompanyListEditorDescriptor.class).in(Singleton.class);
		bind(CompanyListEditor.class);
	}

}
