package com.omdasoft.orderonline.gwt.order.client.companyAdd.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.presenter.CompanyAddPresenter;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;

/**
 * @author sunny
 * @since 2012年6月15日
 */
public class CompanyAddEditor extends AbstractEditor {

	final CompanyAddPresenter companyAddPresenter;
	Object model;

	@Inject
	protected CompanyAddEditor(CompanyAddEditorDescriptor editorDescriptor,
			CompanyAddPresenter companyAddPresenter) {
		super(editorDescriptor);
		this.companyAddPresenter = companyAddPresenter;
	}

	@Override
	public Widget asWidget() {
		return companyAddPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		companyAddPresenter.unbind();
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
			companyAddPresenter.initCompanyUpdate((String)model);
		companyAddPresenter.bind();
	}
}
