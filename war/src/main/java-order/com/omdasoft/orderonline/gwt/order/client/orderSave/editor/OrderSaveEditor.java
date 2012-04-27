package com.omdasoft.orderonline.gwt.order.client.orderSave.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderSave.presenter.OrderSavePresenter;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class OrderSaveEditor extends AbstractEditor {

	final OrderSavePresenter OrderSavePresenter;
	Object model;

	@Inject
	protected OrderSaveEditor(DictionaryListEditorDescriptor editorDescriptor,
			OrderSavePresenter OrderSavePresenter) {
		super(editorDescriptor);
		this.OrderSavePresenter = OrderSavePresenter;
	}

	@Override
	public Widget asWidget() {
		return OrderSavePresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		OrderSavePresenter.unbind();
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
			OrderSavePresenter.initOrder((String)model);
		OrderSavePresenter.bind();
	}
}
