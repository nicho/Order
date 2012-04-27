package com.omdasoft.orderonline.gwt.order.client.orderList.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderList.presenter.OrderListPresenter;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class OrderListEditor extends AbstractEditor {

	final OrderListPresenter orderListPresenter;
	Object model;

	@Inject
	protected OrderListEditor(DictionaryListEditorDescriptor editorDescriptor,
			OrderListPresenter orderListPresenter) {
		super(editorDescriptor);
		this.orderListPresenter = orderListPresenter;
	}

	@Override
	public Widget asWidget() {
		return orderListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		orderListPresenter.unbind();
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
		orderListPresenter.bind();
	}
}
