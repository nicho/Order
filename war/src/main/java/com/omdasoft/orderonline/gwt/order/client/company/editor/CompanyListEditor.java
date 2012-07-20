package com.omdasoft.orderonline.gwt.order.client.company.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.company.presenter.CompanyListPresenter;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;

public class CompanyListEditor extends AbstractEditor {

	Object model;
	CompanyListPresenter companyListPresenter;
	
	@Inject
	protected CompanyListEditor(CompanyListEditorDescriptor editorDescriptor,CompanyListPresenter companyListPresenter) {
		super(editorDescriptor);
		this.companyListPresenter = companyListPresenter;
	}


	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {
	}

	public Widget asWidget() {
		return companyListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		return true;
	}

	public void setModel(Object model) {
		this.model = model;
		companyListPresenter.bind();
	}

}
