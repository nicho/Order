package com.omdasoft.orderonline.gwt.order.client.registerHr.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.registerHr.presenter.RegisterHrPresenter;

public class RegisterHrEditor extends AbstractEditor {

	Object model;
	RegisterHrPresenter registerHrPresenter;
	
	@Inject
	protected RegisterHrEditor(RegisterHrEditorDescriptor editorDescriptor,RegisterHrPresenter registerHrPresenter) {
		super(editorDescriptor);
		this.registerHrPresenter = registerHrPresenter;
	}


	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {
	}

	public Widget asWidget() {
		return registerHrPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		return true;
	}

	public void setModel(Object model) {
		this.model = model;
		registerHrPresenter.initRegisterCorp((String)model);
		registerHrPresenter.bind();
	}

}
