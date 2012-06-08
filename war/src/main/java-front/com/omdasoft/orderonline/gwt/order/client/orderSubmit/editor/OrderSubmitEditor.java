package com.omdasoft.orderonline.gwt.order.client.orderSubmit.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.presenter.OrderSubmitPresenter;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class OrderSubmitEditor extends AbstractEditor {

	final OrderSubmitPresenter OrderSubmitPresenter;
	Object model;

	@Inject
	protected OrderSubmitEditor(DictionaryListEditorDescriptor editorDescriptor,
			OrderSubmitPresenter OrderSubmitPresenter) {
		super(editorDescriptor);
		this.OrderSubmitPresenter = OrderSubmitPresenter;
	}

	@Override
	public Widget asWidget() {
		return OrderSubmitPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		OrderSubmitPresenter.unbind();
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

		OrderSubmitPresenter.bind();
	}
}
