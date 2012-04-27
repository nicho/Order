package com.omdasoft.orderonline.gwt.order.client.dictionarySave.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.presenter.DictionarySavePresenter;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class DictionarySaveEditor extends AbstractEditor {

	final DictionarySavePresenter DictionarySavePresenter;
	Object model;

	@Inject
	protected DictionarySaveEditor(DictionaryListEditorDescriptor editorDescriptor,
			DictionarySavePresenter DictionarySavePresenter) {
		super(editorDescriptor);
		this.DictionarySavePresenter = DictionarySavePresenter;
	}

	@Override
	public Widget asWidget() {
		return DictionarySavePresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		DictionarySavePresenter.unbind();
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
			DictionarySavePresenter.initDictionaryType((Integer)model);
		DictionarySavePresenter.bind();
	}
}
