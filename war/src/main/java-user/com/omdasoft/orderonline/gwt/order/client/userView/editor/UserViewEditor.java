package com.omdasoft.orderonline.gwt.order.client.userView.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.userView.presenter.UserViewPresenter;

/**
 * @author nicho
 * @since 2012年2月15日 09:22:34
 */
public class UserViewEditor extends AbstractEditor {

	final UserViewPresenter UserViewPresenter;
	Object model;

	@Inject
	protected UserViewEditor(UserViewEditorDescriptor editorDescriptor,
			UserViewPresenter UserViewPresenter) {
		super(editorDescriptor);
		this.UserViewPresenter = UserViewPresenter;
	}

	@Override
	public Widget asWidget() {
		return UserViewPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		UserViewPresenter.unbind();
		return true;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(Object model) {
		this.model = model;
		UserViewPresenter.initUserView((String)model);
		UserViewPresenter.bind();
	}
}
