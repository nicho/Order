/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userAdd.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.userAdd.plugin.UserAddConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class UserAddEditorDescriptor implements EditorDescriptor {

	final Provider<UserAddEditor> editProvider;

	@Inject
	UserAddEditorDescriptor(Provider<UserAddEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return UserAddConstants.EDITOR_STAFFADD_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		UserAddEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("添加员工");
		e.setModel(model);
		return e;
	}

}
