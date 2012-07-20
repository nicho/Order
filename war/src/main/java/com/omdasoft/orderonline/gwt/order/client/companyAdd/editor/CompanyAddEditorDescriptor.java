/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.companyAdd.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.plugin.CompanyAddConstants;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class CompanyAddEditorDescriptor implements EditorDescriptor {

	final Provider<CompanyAddEditor> editProvider;

	@Inject
	CompanyAddEditorDescriptor(Provider<CompanyAddEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return CompanyAddConstants.EDITOR_COMPANYADD_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		CompanyAddEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("添加公司");
		e.setModel(model);
		return e;
	}

}
