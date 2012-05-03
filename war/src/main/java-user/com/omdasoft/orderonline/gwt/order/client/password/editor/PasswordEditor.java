package com.omdasoft.orderonline.gwt.order.client.password.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.password.presenter.PasswordPresenter;

/**

 * @author lw

 */
public class PasswordEditor extends AbstractEditor {

	final PasswordPresenter passwordPresenter;
	Object model;

	@Inject
	protected PasswordEditor(PasswordEditorDescriptor editorDescriptor,
			PasswordPresenter passwordPresenter) {
		super(editorDescriptor);
		this.passwordPresenter = passwordPresenter;
	}

	@Override
	public Widget asWidget() {
		return passwordPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		passwordPresenter.unbind();
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
		passwordPresenter.bind();
	}
}
