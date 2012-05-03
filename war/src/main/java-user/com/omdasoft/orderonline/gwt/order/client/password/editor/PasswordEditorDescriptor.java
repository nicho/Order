/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.password.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.password.plugin.PasswordConstants;


public class PasswordEditorDescriptor implements EditorDescriptor {

	final Provider<PasswordEditor> editProvider;

	@Inject
	PasswordEditorDescriptor(Provider<PasswordEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return PasswordConstants.EDITOR_PASSWORD_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		PasswordEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("密码修改");

		e.setModel(model);
		return e;
	}

}
