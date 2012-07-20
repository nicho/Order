/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.companyAdd.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.editor.CompanyAddEditor;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.editor.CompanyAddEditorDescriptor;

/**
 * @author sunny
 */
public class CompanyAddPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(CompanyAddPluginDescriptor.class).in(Singleton.class);

		bind(CompanyAddEditorDescriptor.class).in(Singleton.class);
		bind(CompanyAddEditor.class);
	}

}
