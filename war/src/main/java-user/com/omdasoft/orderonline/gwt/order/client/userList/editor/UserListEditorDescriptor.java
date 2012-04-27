/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.userList.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.userList.plugin.UserListConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class UserListEditorDescriptor implements EditorDescriptor {

	final Provider<UserListEditor> editProvider;

	@Inject
	UserListEditorDescriptor(Provider<UserListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return UserListConstants.EDITOR_USERLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		UserListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("员工列表");
		e.setModel(model);
		return e;
	}

}
