package com.omdasoft.orderonline.gwt.order.client.dishesSave.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.presenter.DishesSavePresenter;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class DishesSaveEditor extends AbstractEditor {

	final DishesSavePresenter DishesSavePresenter;
	Object model;

	@Inject
	protected DishesSaveEditor(DictionaryListEditorDescriptor editorDescriptor,
			DishesSavePresenter DishesSavePresenter) {
		super(editorDescriptor);
		this.DishesSavePresenter = DishesSavePresenter;
	}

	@Override
	public Widget asWidget() {
		return DishesSavePresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		DishesSavePresenter.unbind();
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
			DishesSavePresenter.initDishes((String)model);
		DishesSavePresenter.bind();
	}
}
