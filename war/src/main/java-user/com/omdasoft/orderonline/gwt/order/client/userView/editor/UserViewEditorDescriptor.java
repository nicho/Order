/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userView.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.userView.plugin.UserViewConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class UserViewEditorDescriptor implements EditorDescriptor {

	final Provider<UserViewEditor> editProvider;

	@Inject
	UserViewEditorDescriptor(Provider<UserViewEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return UserViewConstants.EDITOR_STAFFVIEW_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		UserViewEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("员工查看");
		e.setModel(model);
		return e;
	}

}
