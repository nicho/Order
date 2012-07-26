package com.omdasoft.orderonline.gwt.order.client.registerHr.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.registerHr.plugin.RegisterHrConstants;

public class RegisterHrEditorDescriptor implements EditorDescriptor {

	Provider<RegisterHrEditor> editorProvider;

	@Inject
	RegisterHrEditorDescriptor(Provider<RegisterHrEditor> editorProvider) {
		// as new Instance of EnterpriseEditor should be provided
		// so use provider instead
		this.editorProvider = editorProvider;
	}

	public Editor createEditor(String instanceId, Object model) {
		// as new Instance of EnterpriseEditor should be provided
		// whether the instance is new or not is determined by the binder
		// if Singleton, then provider always return the same EnterpriseEditor
		RegisterHrEditor e = editorProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("餐厅管理员注册");
		e.setModel(model);
		return e;
	}

	public String getEditorId() {
		return RegisterHrConstants.EDITOR_REGISTERHR_EDIT;
	}

}
