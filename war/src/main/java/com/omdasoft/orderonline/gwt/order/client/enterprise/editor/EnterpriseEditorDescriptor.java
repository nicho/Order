package com.omdasoft.orderonline.gwt.order.client.enterprise.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.enterprise.plugin.EnterpriseConstants;

public class EnterpriseEditorDescriptor implements EditorDescriptor {

	Provider<EnterpriseEditor> editorProvider;

	@Inject
	EnterpriseEditorDescriptor(Provider<EnterpriseEditor> editorProvider) {
		// as new Instance of EnterpriseEditor should be provided
		// so use provider instead
		this.editorProvider = editorProvider;
	}

	public Editor createEditor(String instanceId, Object model) {
		// as new Instance of EnterpriseEditor should be provided
		// whether the instance is new or not is determined by the binder
		// if Singleton, then provider always return the same EnterpriseEditor
		EnterpriseEditor e = editorProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("企业注册");
		e.setModel(model);
		return e;
	}

	public String getEditorId() {
		return EnterpriseConstants.EDITOR_ENTERPRISE_EDIT;
	}

}
