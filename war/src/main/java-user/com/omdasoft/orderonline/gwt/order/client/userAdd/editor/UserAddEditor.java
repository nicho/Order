package com.omdasoft.orderonline.gwt.order.client.userAdd.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.userAdd.presenter.UserAddPresenter;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class UserAddEditor extends AbstractEditor {

	final UserAddPresenter staffAddPresenter;
	Object model;

	@Inject
	protected UserAddEditor(UserAddEditorDescriptor editorDescriptor,
			UserAddPresenter staffAddPresenter) {
		super(editorDescriptor);
		this.staffAddPresenter = staffAddPresenter;
	}

	@Override
	public Widget asWidget() {
		return staffAddPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		staffAddPresenter.unbind();
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
		if(model!=null)
			staffAddPresenter.initStaffUpdate((String)model);
		staffAddPresenter.bind();
	}
}
